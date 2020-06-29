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

(ns async2.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go chan >! <! timeout)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 15000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")
    ; vytvorime kanal
    (let [channel (chan)]
        ; kontinualni posilani zprav do kanalu v asynchronnim bloku
        (go
            (dotimes [i 10]
                ;(Thread/sleep 1000)
                (<! (timeout 1000))
                (>! channel i)))

        (println "1st go block started")

        ; kontinualni cteni zprav z kanalu v asynchronnim bloku
        (go
            (while true
                (println (<! channel))))

        (println "2nd go block started"))
    ; chvili pockame, az se vypise cela sekvence 0 az 9
    (wait)
    (println "Finish"))

