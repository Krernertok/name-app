(ns name-app.data-test
  (:require [clojure.test :refer [deftest testing is]]
            [name-app.data :refer [get-amount-for-name]]))

(deftest test-get-name
  (testing "Getting amount by name"
    (testing "Existing name"
      (is (= (get-amount-for-name "Henna") 4)))
    (testing "Non-existing name"
      (is (= (get-amount-for-name "Archibald") nil)))))