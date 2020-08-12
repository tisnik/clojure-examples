(defn compute-pi
  ([n pi]
  (loop [i 3
         pi pi]
    (if (<= i (+ n 2))
      (recur (+ i 2) (* pi (/ (- i 1) i) (/ (+ i 1) i)))
      pi)))
  ([n]
   (compute-pi n 4.0)))

(let [n (range 1000000 1000020)
      results (doall (pmap #(compute-pi %) n))]
  (doseq [pi (doall results)]
    (println pi)))
