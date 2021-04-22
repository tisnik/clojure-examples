#!/usr/bin/env bb

;
;  (C) Copyright 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

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
  (doseq [pi results]
    (println pi)))

(System/exit 0)
