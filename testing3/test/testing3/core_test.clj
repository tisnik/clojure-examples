(ns testing3.core-test
    (:require [clojure.test :refer :all]
              [testing3.core :refer :all]))

(defn callable?
    "Test if given function-name is bound to the real function."
    [function-name]
    (clojure.test/function? function-name))

(deftest test-main-existence
    "Check that the testing3.core/-main definition exists."
    (testing "if the testing3.core/-main definition exists."
        (is (callable? 'testing3.core/-main))))

(deftest test-add-existence
    "Check that the testing3.core/add definition exists."
    (testing "if the testing3.core/add definition exists."
        (is (callable? 'testing3.core/add))))

(deftest test-add-1
    (testing "function add"
        (is (= 0 (add 0 0)))
        (is (= 3 (add 1 2)))
        (is (= 5/6 (add 1/2 1/3)))))

