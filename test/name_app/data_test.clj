(ns name-app.data-test
  (:require [clojure.test :refer :all]
            [name-app.data :refer :all]))

(deftest test-get-name
  (testing "Getting amount by name"
    (testing "Existing name"
      (is (= (get-amount-for-name "Henna") 4)))
    (testing "Non-existing name"
      (is (= (get-amount-for-name "Archibald") nil)))))