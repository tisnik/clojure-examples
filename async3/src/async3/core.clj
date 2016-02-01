(ns async3.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go chan >! <!)])

(def logger (chan))

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 5000))

(defn start-logger
    "Spusteni loggeru."
    []
    (go
        (while true
            ;(Thread/sleep 1000) ; zkuste odkomentovat
            ; vytisteni vsech dat prectenych z kanalu
            (println (<! logger)))))

(defn log
    "Zalogovani zpravy - poslani do kanalu."
    [message]
    (go (>! logger message))
    nil)

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")
    (start-logger)
    (log "Hello")

    (dotimes [i 20]
        (future (log (str "Thread #" i))))

    (log "world")

    ; chvili pockame, az se vypise cela sekvence 0 az 9
    (wait)
    ; dokonceni vsech dalsich vlaken atd.
    (shutdown-agents)
    (println "Finish"))

