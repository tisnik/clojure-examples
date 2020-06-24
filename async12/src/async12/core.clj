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

(ns async12.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go merge chan >! <! timeout)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 5000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")

    ; vytvorime tri kanaly
    (let [channel1 (chan) ; bezny kanal
          channel2 (chan) ; bezny kanal
          channel3 (chan) ; bezny kanal
                          ; spojeni tri kanalu do jednoho
          mergedChannels (merge [channel1 channel2 channel3])]

        ; konzument
        (go
            (while true
                (println (<! mergedChannels))))

        (println "consumer started")

        ; prvni producent
        (go
            (doseq [i (map #(* 2 %) (range))]
                (<! (timeout 100))
                (>! channel1 (str "producer#1: " i))))

        (println "producer 1 started")

        ; druhy producent
        (go
            (doseq [i (map #(inc (* 2 %)) (range))]
                (<! (timeout 100))
                (>! channel2 (str "producer#2: " i))))

        (println "producer 2 started")

        ; treti producent
        (go
            (doseq [i (map #(/ 1.0 (inc %)) (range))]
                (<! (timeout 100))
                (>! channel3 (str "producer#3: " i))))

        (println "producer 3 started"))

    ; chvili pockame
    (wait)
    (println "Finish")
    (System/exit 0))

