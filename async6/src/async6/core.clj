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

(ns async6.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go chan >! <! timeout alts!)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 5000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")
    ; vytvorime kanaly
    (let [channel1 (chan)
          channel2 (chan)
          channel3 (chan)]

        ; kontinualni posilani zprav do trech kanalu v trojici asynchronnich bloku
        (go
            (doseq [i (range)]
                (>! channel1 i)))
        (go
            (doseq [i (range)]
                (>! channel2 i)))
        (go
            (doseq [i (range)]
                (>! channel3 i)))

        (println "producers started")

        ; z kanalu se celkem precte 1000 hodnot
        (go
            ; citace pouzite pro vyhodnoceni statistiky cteni
            (let [counter1 (atom 0)
                  counter2 (atom 0)
                  counter3 (atom 0)]
                ; precteme 1000 hodnot
                (dotimes [n 1000]
                    ;(<! (timeout 1)) ; zkuste odkomentovat
                    (let [[item channel] (alts! [channel1 channel2 channel3] :priority true)]
                        ; vyhodnoceni, ze ktereho kanalu se cteni provedlo
                        (condp = channel
                            channel1 (swap! counter1 inc)
                            channel2 (swap! counter2 inc)
                            channel3 (swap! counter3 inc))))
                ; vypis ziskane statistiky
                (println "Channel #1 read " @counter1 "times")
                (println "Channel #2 read " @counter2 "times")
                (println "Channel #3 read " @counter3 "times")))

    ; chvili pockame
    (wait)
    (println "Finish")
    (System/exit 0)))

