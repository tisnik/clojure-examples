(ns codoxtest.core
    "Popis modulu 'core'")

(defn -main
    "I don't do a whole lot ... yet."
    [& args]
    (println "Hello, World!"))

(defn f1
    "Popis funkce f1."
    [])

(defn f2
    "Popis funkce f2."
    [x]
    (inc x))

(defn f3
    "Popis funkce f2."
    [x y]
    (+ x y))

(def answer
    "The Answer to the Ultimate Question of Life, the Universe, and Everything"
    42)

(defmacro unless
    "Makro prevzate z dokumentace Clojure."
    [pred a b]
    `(if (not ~pred) ~a ~b))

