(ns async4.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go chan >! <! timeout)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 10000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")
    ; vytvorime kanal
    (let [channel (chan)]
        ; kontinualni posilani zprav do kanalu v trojici asynchronnich bloku
        (go
            (while true
                (<! (timeout 500))
                (>! channel "first")))
        (go
            (while true
                (<! (timeout 1000))
                (>! channel "second")))
        (go
            (while true
                (<! (timeout 2000))
                (>! channel "third")))

        (println "producers started")

        ; kontinualni cteni zprav z kanalu v asynchronnim bloku
        (go
            (while true
                (println (<! channel))))

        (println "consumer started"))
    ; chvili pockame, az se vypise cela sekvence 0 az 9
    (wait)
    (println "Finish")
    (System/exit 0))

