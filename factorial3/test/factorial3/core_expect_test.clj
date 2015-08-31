(ns factorial3.core-expect-test
  (:require [factorial3.core :refer :all])
  (:use expectations))

(expect 1 (factorial 0))
(expect 1 (factorial 1))

(expect 1       (factorial 0))
(expect 1       (factorial 1))
(expect (* 1 2) (factorial 2))
(expect (* 1 2 3 4 5) (factorial 5))
(expect 720     (factorial 6))

(expect 0 (factorial 0))
(expect 0 (factorial 1))
(expect 0 (factorial 2))

(expect IllegalArgumentException (factorial -1))
(expect IllegalArgumentException (factorial -2))
(expect IllegalArgumentException (factorial -100))

(expect IllegalArgumentException (factorial 1))
(expect IllegalArgumentException (factorial 2))
(expect IllegalArgumentException (factorial 3))

