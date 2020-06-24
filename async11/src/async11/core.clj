;
;  (C) Copyright 2016, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns async11.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go go-loop offer! poll! chan >! <! timeout close!)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 5000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")

    ; vytvorime bezny kanal
    (let [stopper (chan)]

        ; spustime nejaky vypocet
        (go-loop []
            (when (not (poll! stopper)) ; pokusime se cist z kanalu ovsem bez blokovani
                  (print ".")
                  (<! (timeout 20))
                  (recur)))

        (wait)

        ; zastavime predchozi vypocet (opet neblokujicim zpusobem, ale tady je to jedno)
        (go
            (offer! stopper "zastav!")))

    ; chvili pockame
    (wait)
    (println "Finish")
    (System/exit 0))

