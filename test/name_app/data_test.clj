(ns name-app.data-test
  (:require [clojure.test :refer [deftest testing is use-fixtures]]
            [name-app.data :refer :all]
            [name-app.test-utils :refer [test-fixture]]))


(use-fixtures :once test-fixture)

(deftest test-get-names-by-amount
  (testing "Getting names sorted by amount"
    (is (= (get-names-by-amount) '({:name "Roger" :amount 4}
                                   {:name "Michael" :amount 3}
                                   {:name "John" :amount 2})))))

(deftest test-get-names-by-name
  (testing "Getting names sorted by name"
    (is (= (get-names-by-name) '({:name "John" :amount 2}
                                 {:name "Michael" :amount 3}
                                 {:name "Roger" :amount 4})))))

(deftest test-get-total-names
  (testing "Get sum total of amounts"
    (is (= (get-total-names) 9))))

(deftest test-get-name
  (testing "Getting amount by name"
    (testing "Existing name"
      (is (= (get-name-data "Michael") {:name "Michael" :amount 3})))
    (testing "Non-existing name"
      (is (= (get-name-data "Archibald") nil)))))