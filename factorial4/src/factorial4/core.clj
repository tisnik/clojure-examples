(ns factorial4.core
    (:gen-class))

; funkce faktorial obsahuje i test na zaporne hodnoty
(defn factorial
    [n]
    (if (neg? n)
        (throw (IllegalArgumentException. "negative numbers are not supported!"))
        (apply * (range 1 (inc n)))))

; otestujeme funkci faktorial
(defn -main
    [& args]
    (doseq [i (range 0 10)]
        (println i "! = " (factorial i))))

