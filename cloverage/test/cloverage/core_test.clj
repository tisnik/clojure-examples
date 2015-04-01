(ns cloverage.core-test
  (:require [clojure.test :refer :all]
            [cloverage.core :refer :all]))

(deftest factorial-test
    (testing "Factorial"
        (is ( = (factorial 0)   1) "beginning")
        (is ( = (factorial 1)   1) "beginning")
        (is ( = (factorial 2)   (* 1 2)) "still easy")
        (is ( = (factorial 5)   (* 1 2 3 4 5)) "5!")
        (is ( = (factorial 6)   720) "6!")))

(deftest factorial2-test
    (testing "Factorial"
        (is ( = (factorial2 0)   1) "beginning")
        (is ( = (factorial2 1)   1) "beginning")
        (is ( = (factorial2 2)   (* 1 2)) "still easy")
        (is ( = (factorial2 5)   (* 1 2 3 4 5)) "5!")
        (is ( = (factorial2 6)   720) "6!")))

(deftest exception-test
    (testing "If factorial throws exception"
        (is (thrown? IllegalArgumentException (factorial -1)))
        (is (thrown? IllegalArgumentException (factorial -2)))
        (is (thrown? IllegalArgumentException (factorial -100)))))

