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
   [spec-tools.data-spec :as ds])
  (:gen-class))

(def app 
  (ring/ring-handler
   (ring/router
    [["/api/v1"

      ["/names"
       {:get
        {:parameters {:query {(ds/opt :sort-by) string?}}
         :responses {200 {:body string?}}
         :handler handlers/get-names-handler}}]

      ["/names/:name"
       {:get
        {:parameters {:path {:name string?}}
         :responses {200 {:body string?}}
         :handler handlers/get-name-handler}}]

      ["/total-names"
       {:get {:handler handlers/get-total-names-handler}}]]]

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
  ([] (start "3000"))
  ([port]
   (let [port-number (Integer/parseInt port)]
    (jetty/run-jetty #'app {:port port-number :join? false})
    (println "name-app server running on port " port-number))))

(defn -main
  [& args]
  (if (nil? args)
    (start)
    (start (nth args 0))))
