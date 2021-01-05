(ns name-app.data
  (:require [clojure.data.json :as json]))

(def ^:private names (get 
            (json/read-str (slurp "data/names.json")
                           :key-fn keyword) 
            :names))

(def ^:private names-by-amount (reverse (sort-by last names)))

(def ^:private names-by-name (sort-by first names))

(def ^:private total-names (reduce 
                  (fn [total name-map] (+ total (:amount name-map)))
                  0
                  names))


(defn get-names-by-amount
  "Returns a list of name maps sorted according to :amount"
  []
  names-by-amount)

(defn get-names-by-name
  "Returns a list of name maps sorted according to :name"
  []
  names-by-name)

(defn get-total-names
  "Returns the sum of the amounts of names"
  []
  total-names)

(defn get-amount-for-name
  "Returns the amount of the given name or nil if the name is not found"
  [name] 
  (:amount (first (filter #(= (:name %) name) names))))