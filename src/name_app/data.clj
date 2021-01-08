(ns name-app.data
  (:require [clojure.data.json :as json]
            [clojure.string :as string]))

(def ^:private names (get
                      (json/read-str (slurp "data/names.json")
                                     :key-fn keyword)
                      :names))


(defn get-names-by-amount
  "Returns a list of name maps sorted according to :amount"
  []
  (reverse (sort-by last names)))

(defn get-names-by-name
  "Returns a list of name maps sorted according to :name"
  []
  (sort-by first names))

(defn get-total-names
  "Returns the sum of the amounts of names"
  []
  (reduce
   (fn [total name-map] (+ total (:amount name-map)))
   0
   names))

(defn get-name-data
  "Returns the amount of the given name or nil if the name is not found"
  [name]
  (let [clean_name (string/lower-case name)]
    (first (filter #(= (string/lower-case  (:name %)) clean_name) names))))