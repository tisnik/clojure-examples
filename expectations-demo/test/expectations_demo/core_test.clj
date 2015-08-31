(ns expectations-demo.core-test
  (:require [expectations :refer :all]
            [expectations-demo.core :refer :all]))

(expect nil? nil)

(expect seq? '(1 2 3))

(expect seq? (seq [1 2 3]))

(expect #"Expect" "Expectations")

(expect 3 (in [1 2 3]))

(expect [1 2] [3 4])

(expect [1 2] [2 1])

(expect [1 2] [1 2 3])

(expect [1 2 3] [1 2])

