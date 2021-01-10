(ns name-app.data
  (:require [name-app.db :as db]
            [clojure.string :as string]))

(defn get-names-by-amount
  "Returns a list of name maps sorted according to :amount"
  []
  (db/query-names-by-amount))

(defn get-names-by-name
  "Returns a list of name maps sorted according to :name"
  []
  (db/query-names-alphabetically))

(defn get-total-names
  "Returns the sum of the amounts of names"
  []
  (db/query-total-names))

(defn get-name-data
  "Returns the amount of the given name or nil if the name is not found"
  [name]
  (let [name-data (db/query-name name)]
    (if (empty? name-data)
      {:name name :amount 0}
      (first name-data))))