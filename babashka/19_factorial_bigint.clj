(defn factorial
    [n]
    (if (neg? n)
        (throw (IllegalArgumentException. "negative numbers are not supported!"))
        (apply * (range 1N (inc n)))))

(defn main
    [max]
    (doseq [i (range 0 (inc max))]
        (println i "! = " (factorial i))))

(main 50)
