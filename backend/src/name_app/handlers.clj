(ns name-app.handlers
  (:require [clojure.data.json :as json]
            [clojure.string :as str]
            [name-app.data :as data]))

(defn get-names-handler
  [{{{:keys [sort]} :query} :parameters}]
  (if (= sort "amount")
    {:status 200 :body (json/write-str {:names (data/get-names-by-amount)})}
      ;; default to sorting by name
    {:status 200 :body (json/write-str {:names (data/get-names-by-name)})}))

(defn get-name-handler
  [{{{:keys [name]} :path} :parameters}]
  (let [name-data (data/get-name-data name)
        cap-name (str/capitalize name)
        response-body (if (empty? name-data)
                        {:name cap-name, :amount 0}
                        (first name-data))]
    {:status 200 :body (json/write-str response-body)}))

(defn get-total-names-handler
  [_]
  {:status 200 :body (json/write-str  {:total  (data/get-total-names)})})

