(ns name-app.db-test
  (:require [clojure.test :refer [deftest testing is use-fixtures]]
            [name-app.db :as db]
            [name-app.test-utils :refer [test-fixture]]))


(use-fixtures :once test-fixture)

(deftest test-query-names-alphabetically
  (testing "Querying names sorted by name"
    (is (= (db/query-names-alphabetically) '({:name "John" :amount 2}
                                 {:name "Michael" :amount 3}
                                 {:name "Roger" :amount 4})))))

(deftest test-query-names-by-amount
  (testing "Querying names sorted by amount"
    (is (= (db/query-names-by-amount) '({:name "Roger" :amount 4}
                                   {:name "Michael" :amount 3}
                                   {:name "John" :amount 2})))))

(deftest test-query-total-names
  (testing "Get sum total of amounts"
    (is (= (db/query-total-names) 9))))

(deftest test-query-name
  (testing "Getting amount by name"
    (testing "Existing name"
      (is (= (db/query-name "Michael") '({:name "Michael" :amount 3}))))
    (testing "Non-existing name"
      (is (= (db/query-name "Archibald") '())))))