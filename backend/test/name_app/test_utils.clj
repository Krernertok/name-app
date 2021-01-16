(ns name-app.test-utils
  (:require [name-app.db :as db]
            [clojure.java.io :as io]))

(def db-name "data/test.db")

(def test-db-spec
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     db-name})

(def test-data '({:name "Michael" :amount 3}
                 {:name "Roger" :amount 4}
                 {:name "John" :amount 2}))

(defn test-fixture [f]
  (db/create-table test-db-spec "names")
  (db/add-data-into-db test-db-spec "names" test-data)
  (with-redefs [name-app.db/db-spec test-db-spec]
    (f))
  (io/delete-file db-name))
