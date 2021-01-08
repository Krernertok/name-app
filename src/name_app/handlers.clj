(ns name-app.handlers
  (:require [clojure.data.json :as json]
            [name-app.data :as data]))

(defn default-handler
  [req]
  {:status 404 :body "Page not found"})

(defn get-names-handler
  [{{{:keys [sort-by]} :query} :parameters}]
  (if (= sort-by "amount")
    {:status 200 :body (json/write-str {:names (data/get-names-by-amount)})}
      ;; default to sorting by name
    {:status 200 :body (json/write-str {:names (data/get-names-by-name)})}))

(defn get-name-handler
  [{{{:keys [name]} :path} :parameters}]
  {:status 200 :body (json/write-str (data/get-amount-for-name name))})

(defn get-total-names-handler
  [req]
  {:status 200 :body (json/write-str {:total-names (data/get-total-names)})})

