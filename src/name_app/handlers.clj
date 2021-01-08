(ns name-app.handlers
  (:require [clojure.data.json :as json]
            [clojure.string :as str]
            [name-app.data :as data]))

(defn get-names-handler
  [{{{:keys [sort-by]} :query} :parameters}]
  (if (= sort-by "amount")
    {:status 200 :body (json/write-str {:names (data/get-names-by-amount)})}
      ;; default to sorting by name
    {:status 200 :body (json/write-str {:names (data/get-names-by-name)})}))

(defn get-name-handler
  [{{{:keys [name]} :path} :parameters}]
  (let [name-data (data/get-name-data name)
        response-body (or name-data 
                          {:name (str/capitalize name), :amount 0})]
    {:status 200 :body (json/write-str response-body)}))

(defn get-total-names-handler
  [req]
  {:status 200 :body (json/write-str {:total-names (data/get-total-names)})})

