(ns name-app.db
     (:require [clojure.java.jdbc :as jdbc]
               [clojure.data.json :as json]))

(def db-spec
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "data/names.db"})

(def table :names)
(def query-table (name table))

(defn query-names-alphabetically
  []
  (jdbc/query db-spec 
              [(str "SELECT * FROM " query-table " ORDER BY name ASC")]))

(defn query-names-by-amount
  []
  (jdbc/query db-spec [(str "SELECT * FROM " query-table " ORDER BY amount DESC")]))

(defn query-name
  [name]
  (jdbc/query db-spec [(str "SELECT * FROM " query-table " WHERE name = \"" name "\" COLLATE NOCASE")]))

(defn query-total-names
  []
  (jdbc/query db-spec [(str "SELECT SUM(amount) AS total FROM " query-table)]))


(defn create-table
  "Create table for names"
  [db-spec table]
  (jdbc/db-do-commands db-spec
                       (jdbc/create-table-ddl table
                                              [[:name :text]
                                               [:amount :int]]
                                              {:conditional? true})))

(defn add-data-into-db
  [db-spec table data]
  (jdbc/insert-multi! db-spec table data))

(defn -main
  "Create and populate database with data from JSON file"
  [& args]
  (try (let [data (:names (json/read-str (slurp "data/names.json")
                                         :key-fn keyword))]
         (println data)
         (create-table db-spec query-table)
         (add-data-into-db db-spec query-table data))
       (catch Exception e
         (.printStackTrace e)
         (println (str "Error during DB creation: " (.getMessage e))))))