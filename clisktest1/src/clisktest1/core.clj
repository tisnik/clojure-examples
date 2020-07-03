;
;  (C) Copyright 2015, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns clisktest1.core
    (:gen-class)
    (:use clisk.live))

(defn -main
    "Zaciname"
    [& args]
    ; funkce "show" zobrazí vzorek
    (show (checker black white))
    (show (checker yellow blue) :size 512)
    ; nutno odkomentovat v případě, že se má aplikace automaticky ukončit
    ;(System/exit 0)
)

