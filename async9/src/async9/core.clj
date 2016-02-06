(ns async9.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go chan >! <! timeout sliding-buffer close!)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 5000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")
    ; vytvorime kanal s sliding bufferem o zadane kapacite
    (let [channel (chan (sliding-buffer 10))]

        ; cteni zprav z kanalu (to zahajime drive, at je jistota
        ; ze zapisy neskonci moc brzo)
        (go
            (loop [result []]
                (<! (timeout 1))
                (let [item (<! channel)] ; pokud je kanal zavreny, vrati se nil
                    (if item             ; v pripade, ze se prvek precetl
                       (recur (conj result item)) ; prida se do kolekce
                       (println result)))))       ; jinak koncime

        (println "consumer started")

        ; poslani zprav do kanalu, celkem 1000 hodnot od 0 do 999
        (go
            (doseq [i (range 0 1000)]
                (>! channel i))
            (close! channel)))

        (println "producer started")

    ; chvili pockame
    (wait)
    (println "Finish")
    (System/exit 0))

