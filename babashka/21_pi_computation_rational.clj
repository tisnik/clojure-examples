(defn compute-pi
  ([n pi]
  (loop [i 3
         pi pi]
    (if (<= i (+ n 2))
      (recur (+ i 2) (* pi (/ (- i 1) i) (/ (+ i 1) i)))
      pi)))
  ([n]
   (compute-pi n 4)))


(doseq [i (range 0 6)]
  (let [n (bit-shift-left 1 i)]
    (println n "\t" (compute-pi n))))
