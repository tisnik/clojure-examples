(ns testing3.core
    (:gen-class))

(defn add
    [x y]
    (println "Adding" x "to" y)
    (+ x y))

(defn -main
    [& args]
    (println (add 1 2)))

