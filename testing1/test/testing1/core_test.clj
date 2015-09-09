(ns testing1.core-test
    (:require [clojure.test :refer :all]
              [testing1.core :refer :all]))

(deftest test-add-1
    (testing "function add"
        (is (= 0 (add 0 0)))
        (is (= 3 (add 1 2)))
        (is (= 5/6 (add 1/2 1/3)))))

(deftest test-add-2
    (testing "function add"
        (are [x y] (= x y)
            0   (add 0 0)
            3   (add 1 2)
            5/6 (add 1/2 1/3))))

(deftest test-add-3
    (testing "function add"
        (is (= "Adding 0 to 0\n"     (with-out-str (add 0 0))))
        (is (= "Adding 1 to 2\n"     (with-out-str (add 1 2))))
        (is (= "Adding 1/2 to 1/3\n" (with-out-str (add 1/2 1/3))))))

(deftest test-add-4
    (testing "function add"
        (are [x y] (= x y)
            "Adding 0 to 0\n"     (with-out-str (add 0 0))
            "Adding 1 to 2\n"     (with-out-str (add 1 2))
            "Adding 1/2 to 1/3\n" (with-out-str (add 1/2 1/3)))))

