(ns testing2.core-test
  (:require [clojure.test :refer :all]
            [testing2.core :refer :all]))

(require '[clojure.java.jdbc :as jdbc])

(deftest test-read-changes-for-user
    (testing "read-changes-for-user"
        ; use mock instead of jdbc/query
        (with-redefs [jdbc/query (fn [db-spec query] (second query))]
            (is (= "Pavel" (read-changes-for-user "Pavel"))))))

(deftest test-count-changes-for-user
    (testing "read-changes-for-user"
        ; use mock instead of jdbc/query
        (with-redefs [jdbc/query (fn [db-spec query] [1 2 3 4 5 6 7 8 9 10])]
            (is (= 10 (count-changes-for-user "Pavel"))))))

