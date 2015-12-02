; Examples for Wisp: Homoiconic JS with clojure syntax, s-expressions & macros
;
;  (C) Copyright 2015  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

; Test překladu programových smyček do JavaScriptu


; počitadlo od 0 do 10
(loop [i 0]
    (if (== i 10)
        sum
        (recur (+ i 1))))

; výpočet desáté mocniny dvojky
(loop [i 0 sum 1]
    (if (== i 10)
        sum
        (recur (+ i 1) (* sum 2))))

; funkce pro výpočet n-té mocniny dvojky
(defn pow2
    [n]
    (loop [i 0 sum 1]
        (if (== i n)
            sum
            (recur (+ i 1) (* sum 2)))))

; příklad pro výpočet sumy pole
(def array [1 2 3 4 5])

(loop [i 0 sum 0]
    (if (== i 5)
        sum
        (recur (+ i 1) (+ sum (get array i) ))))

