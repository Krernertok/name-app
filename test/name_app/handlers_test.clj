(ns name-app.handlers-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [name-app.handlers :as handlers]
            [name-app.test-utils :refer [test-fixture]]))

(use-fixtures :once test-fixture)

(def names-alphabetically-json
  "{\"names\":[{\"name\":\"John\",\"amount\":2},{\"name\":\"Michael\",\"amount\":3},{\"name\":\"Roger\",\"amount\":4}]}")

(def names-by-amount-json
  "{\"names\":[{\"name\":\"Roger\",\"amount\":4},{\"name\":\"Michael\",\"amount\":3},{\"name\":\"John\",\"amount\":2}]}")


(deftest test-get-names-handler
  (testing "Testing /api/v1/names"
    (testing "Testing without query parameter"
      (is (= (handlers/get-names-handler nil)
             {:status 200
              :body names-alphabetically-json})))
    (testing "Testing with sort-by=\"amount\""
      (is (= (handlers/get-names-handler {:parameters {:query {:sort-by "amount"}}})
             {:status 200
              :body names-by-amount-json})))
    (testing "Testing with sort-by=\"name\""
      (is (= (handlers/get-names-handler {:parameters {:query {:sort-by "name"}}})
             {:status 200
              :body names-alphabetically-json})))
    (testing "Testing with sort-by=\"random\""
      (is (= (handlers/get-names-handler {:parameters {:query {:sort-by "random"}}})
             {:status 200
              :body names-alphabetically-json})))))

(deftest test-total-names-handler
  (testing "Testing /api/v1/total-names"
    (is (= (handlers/get-total-names-handler nil)
           {:status 200
            :body "{\"total-names\":9}"}))))

(deftest test-get-name-handler
  (testing "Testing /api/v1/names/:name"
    (testing "Testing with valid name"
      (is (= (handlers/get-name-handler {:parameters {:path {:name "John"}}})
             {:status 200
              :body "{\"name\":\"John\",\"amount\":2}"})))
    (testing "Testing with invalid name"
      (is (= (handlers/get-name-handler {:parameters {:path {:name "Rupert"}}})
             {:status 200
              :body "{\"name\":\"Rupert\",\"amount\":0}"})))))