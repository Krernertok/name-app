(ns name-app.test-utils
  (:require [name-app.data]))

(def test-data '({:name "Michael" :amount 3}
                 {:name "Roger" :amount 4}
                 {:name "John" :amount 2}))

(defn test-fixture [f]
  (with-redefs [name-app.data/names test-data] (f)))