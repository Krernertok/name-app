(ns name-app.handlers
  (:require [clojure.data.json :as json]
            [clojure.string :as str]
            [name-app.data :as data]))

(def cors-headers {"Access-Control-Allow-Origin" "*"
                   "Access-Control-Allow-Headers" "Origin, Accept, Access-Control-Request-Method, Access-Control-Allow-Headers, Content-Type, *"})
(defn ok-response
 [body] 
  {:status 200
   :headers cors-headers
   :body body})

(defn get-names-handler
  [{{{:keys [sort]} :query} :parameters}]
  (if (= sort "amount")
    (ok-response (json/write-str {:names (data/get-names-by-amount)}))
      ;; default to sorting by name
    (ok-response (json/write-str {:names (data/get-names-by-name)}))))

(defn get-name-handler
  [{{{:keys [name]} :path} :parameters}]
  (let [name-data (data/get-name-data name)
        cap-name (str/capitalize name)
        response-body (if (empty? name-data)
                        {:name cap-name, :amount 0}
                        (first name-data))]
    (ok-response (json/write-str response-body))))

(defn get-total-names-handler
  [_]
  (ok-response (json/write-str  {:total  (data/get-total-names)})))