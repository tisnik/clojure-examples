(ns factorial2.core-test
  (:require [clojure.test :refer :all]
            [factorial2.core :refer :all]))

(deftest factorial-test
    (testing "Factorial"
        (is ( = (factorial 0)   1) "beginning")
        (is ( = (factorial 1)   1) "beginning")
        (is ( = (factorial 2)   (* 1 2)) "still easy")
        (is ( = (factorial 5)   (* 1 2 3 4 5)) "5!")
        (is ( = (factorial 6)   720) "6!")))

(deftest exception-test
    (testing "If factorial throws exception"
        (is (thrown? IllegalArgumentException (factorial -1)))
        (is (thrown? IllegalArgumentException (factorial -2)))
        (is (thrown? IllegalArgumentException (factorial -100)))))

