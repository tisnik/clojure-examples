(ns main)

(require '[clj-time.core :as t])
(require '[clj-time.format :as f])

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

(defn time-str
    "Returns a string representation of a datetime in the local time zone."
    [dt]
    (f/unparse
        (f/with-zone (f/formatter "hh:mm aa") (t/default-time-zone))
        dt))

(defn -main
    []
    (println "Hello world, the time is" (time-str (t/now)))
    (matrix-tests))
