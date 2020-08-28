;
;  (C) Copyright 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns cucumber+expect3.core
    (:gen-class))

; funkce faktorial obsahuje i test na zaporne hodnoty
(defn factorial
    [n]
    (if (neg? n)
        (throw (IllegalArgumentException. "negative numbers are not supported!"))
        (apply * (range 1M (inc n)))))

; otestujeme funkci faktorial
(defn -main
    [& args]
    (doseq [i (range 0 10)]
        (println i "! = " (factorial i))))

