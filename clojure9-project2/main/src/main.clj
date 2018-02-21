(ns main)

(require '[time-utils :as t])
(require '[matrix :as m])

(defn -main
    []
    (println "Hello world, the time is" (t/time-str (t/now)))
    (m/matrix-tests))
