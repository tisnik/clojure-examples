(ns matrix3.core
  (:gen-class))

(use 'clojure.core.matrix)
(use 'clojure.core.matrix.operators)

(defmacro print-vector
    "Makro, které na standardní výstup vypíše výraz a následně i
     hodnotu tohoto výrazu s použitím funkce pm (pretty print matrix).
     Předpokládá se, že se výraz vyhodnotí na vektor."
    [expression]
    `(do (printf "    %-32s =  " '~expression)
         (pm ~expression)))

(defmacro print-matrix
    "Makro, které na standardní výstup vypíše výraz a následně i
     hodnotu tohoto výrazu s použitím funkce pm (pretty print matrix).
     Předpokládá se, že se výraz vyhodnotí na matici"
    [expression]
    `(do (println "   " '~expression "=")
         (pm ~expression)
         (println)))

(defn vector-tests
    "Test chování funkcí emap, eseq a ereduce pro vektory."
    [vector1]
    (println "*** Test chování funkcí emap, eseq a ereduce pro vektory ***")
    (println "Vstupní data (vektor):")
    (print-vector vector1)
    (println "Vyhodnocení:")
    (print-vector (emap inc vector1))
    (print-vector (emap #(+ % 10) vector1))
    (print-vector (ereduce + vector1))
    (print-vector (apply * (eseq vector1)))
    (print-vector (sort (eseq vector1)))
    (print-vector (filter zero? (eseq vector1)))
    (println))

(defn matrix-tests
    "Test chování funkcí emap, eseq a ereduce pro matice."
    [matrix]
    (println "Vstupní data (matice):")
    (print-matrix matrix)
    (println "Vyhodnocení:")
    (print-vector (emap inc matrix))
    (print-vector (emap #(+ % 10) matrix))
    (print-vector (ereduce + matrix))
    (print-vector (apply * (eseq matrix)))
    (print-vector (sort (eseq matrix)))
    (print-vector (filter zero? (eseq matrix)))
    (println))

(defn -main
    "Vítejte v Matrixu počtvrté..."
    [& args]
    (let [v0 (zero-vector 3)
          v1 (array [1 2 3])
          v2 (array [2 3 4])
          v3 (array [1 1])]
          (doseq [selected-vector [v0 v1 v2 v3]]
              (vector-tests selected-vector)))
    (let [m0 (zero-matrix 3 3)
          m1 (identity-matrix 3 3)
          m2 (array [[1 2 3] [4 5 6] [7 8 9]])
          m3 (array [[0 1] [2 3]])]
          (doseq [selected-matrix [m0 m1 m2 m3]]
              (matrix-tests selected-matrix))))

