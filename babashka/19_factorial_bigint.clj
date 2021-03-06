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
