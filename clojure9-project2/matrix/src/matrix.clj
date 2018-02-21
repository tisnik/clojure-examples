(ns matrix)

(require '[clojure.core.matrix :as m])
(require '[clojure.core.matrix.operators :as o])

(defn matrix-tests
    []
    (let [M (m/matrix [[1 2 3] [4 5 6] [7 8 9]])
          v (m/matrix [1/2 2/3 3/4])]
         (m/pm M)
         (m/pm v)
         (m/pm (o/* v 2))
         (m/pm (m/mul M v))
         (m/pm (o/* M M))
         (m/pm (m/inner-product M v))))

