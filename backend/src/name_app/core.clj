(ns name-app.core
  (:require
   [muuntaja.core :as m]
   [name-app.handlers :as handlers]
   [ring.adapter.jetty :as jetty]
   [reitit.coercion.spec]
   [reitit.ring :as ring]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]
   [spec-tools.data-spec :as ds])
  (:gen-class))

(def default-port "3001")

(def app
  (ring/ring-handler
   (ring/router
    [["/swagger.json" {:no-doc true
                       :get {:swagger {:info {:title "Names API"}}
                             :handler (swagger/create-swagger-handler)}}]

     ["/api"

      ["/doc*" {:no-doc true
                :get (swagger-ui/create-swagger-ui-handler)}]

      ["/v1"
       {:swagger {:tags ["/api/v1"]}}

       ["/names"
        {:get
         {:summary "Returns an array of all recorded names and their amounts."
          :description "Defaults to sorting name-amount pairs by name. Sorting options are \"amount\" and \"name\"."
          :parameters {:query {(ds/opt :sort) string?}}
          :responses {200 {:body string?}}
          :handler handlers/get-names-handler}}]

       ["/names/:name"
        {:get
         {:summary "Returns an object with the name and its amount."
          :parameters {:path {:name string?}}
          :responses {200 {:body string?}}
          :handler handlers/get-name-handler}}]

       ["/total-names"
        {:get {:summary "Returns the sum total of all recorded name amounts."
               :handler handlers/get-total-names-handler}}]]]]

    {:data {:coercion reitit.coercion.spec/coercion
            :muuntaja m/instance
            :middleware [parameters/parameters-middleware
                         muuntaja/format-negotiate-middleware
                         muuntaja/format-response-middleware
                         exception/exception-middleware
                         muuntaja/format-request-middleware
                         coercion/coerce-response-middleware
                         coercion/coerce-request-middleware]}})
   
   (ring/create-default-handler
    {:not-found (constantly {:status 404, :body "Resource not found"})
     :method-not-allowed (constantly {:status 405, :body "Method not allowed"})})))
                           

(defn start
  ([] (start default-port))
  ([port]
   (let [port-number (Integer/parseInt port)]
    (jetty/run-jetty #'app {:port port-number :join? false})
    (println "name-app server running on port " port-number))))

(defn -main
  [& args]
  (if (nil? args)
    (start)
    (start (first args))))
