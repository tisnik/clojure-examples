(ns expectations-demo.core-test
  (:require [expectations :refer :all]
            [expectations-demo.core :refer :all]))



; ------------------------------------------------------------
; Porovnání návratové hodnoty
; ------------------------------------------------------------

(expect 42 (* 6 7))

(expect "Hello world" (str "Hello" " " "world"))

(expect 2/3 (/ 2 3))



; ------------------------------------------------------------
; Prvním parametrem makra expect může být například i predikát
; ------------------------------------------------------------

(expect even? 2)

(expect odd? 3)

(expect nil? nil)

(expect seq? '(1 2 3))

(expect seq? (seq [1 2 3]))



; ------------------------------------------------------------
; Pokud je prvním parametrem regexp, bude se "matchovat"
; s řetězcem, který je očekáván druhý parametr (či výsledek
; volané funkce/makra).
; ------------------------------------------------------------

(expect #"Expect" "Expectations")

(expect #"^[a-zA-Z]+$" "Hello")

(expect #"^[a-zA-Z]+$" "123qwe") ; nebude splněno

(expect #"^[a-zA-Z0-9]+$" "123qwe") ; bude splněno

(expect #"[\s]*" "123qwe")

(expect #"^([A-Z][a-z]+)+$" "CamelCaseString")  ; bude splněno

(expect #"^([A-Z][a-z]+)+$" "CamelCaseStringS") ; nebude splněno

(expect #"^([A-Z][a-z]+)+$" "camel_case_string") ; nebude splněno



; ------------------------------------------------------------
; Velmi elegantní je práce s kolekcemi
; ------------------------------------------------------------

; zjištění existence prvku v kolekci
(expect 3 (in [1 2 3]))

; porovnání dvou různých kolekcí
(expect [1 2] [3 4])

(expect [1 2] [3 4 5 6])

; různé typy, stejný obsah - test projde
(expect [1 2] '(1 2))

; expect rozpozná zpřeházené prvky
(expect [1 2] [2 1])
(expect [1 2 3] [3 2 1])

; expect rozpozná přidání prvku
(expect [1 2] [1 2 3])
(expect [1 2] [1 2 3 4 5])

; expect rozpozná i ubrání prvku
(expect [1 2 3] [1 2])
(expect [1 2 3 4 5] [1 2])

; dtto pro mapy - opět se eliminuje výpis zbytečných informací
(expect #{:name "Bender" :id 42} #{:name "Bender" :id 42})

(expect #{:name "Bender" :id 42} #{:name "Joe" :id 42})

(expect #{:name "Bender" :id 42} #{:name "Joe" :id 1000})

(expect #{:name "Bender" :id 42} #{:name "Bender" :id 42 :foo :bar})

(expect #{:name "Bender" :id 42} #{:name "Bender"})

(expect #{:name "Bender" :id 42} #{:name "Bender" :not-id 42})



; ------------------------------------------------------------
; Kontrola typu návratové hodnoty
; ------------------------------------------------------------

(expect String (str "Hello world"))

(expect Long (* 6 7))

(expect Long (apply * (range 10)))

(expect Double (/ 1.0 3))

; pozor na použití zlomků
(expect clojure.lang.Ratio (/ 1 3))

; ------------------------------------------------------------
; Kontrola vyhození výjimky
; ------------------------------------------------------------

; výjimka je vyhozena
(expect ArithmeticException (/ 1 0))

; výjimka není vyhozena
(expect IndexOutOfBoundsException (nth [1 2 3] 1))

; výjimka je vyhozena
(expect IndexOutOfBoundsException (nth [1 2 3] -1))

; je vyhozena odlišná výjimka
(expect NullPointerException (nth [1 2 3] -1))

