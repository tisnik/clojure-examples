(ns matrix1.core
    (:gen-class))

(use 'clojure.core.matrix)
(use 'clojure.core.matrix.operators)

(defn -main
  "Vitejte v Matrixu..."
  [& args]
  (let [M (matrix [[1 2] [3 4]])
        v (matrix [1 2])]
        (println M)
        (println v)
        (println (* v 2))
        (println (mul M v))
        (println (* M M))
        (println (inner-product M v))))

