(ns async1.core
    (:gen-class))

; nacteme vsechny potrebne funkce, makra a symboly z knihovny
; (schvalne se nenacitaji vsechny funkce, protoze by jejich jmena
;  prepsala takove zakladni funkce, jako je map apod.)
(require '[clojure.core.async :refer (go chan >! <!)])

(defn wait
    "Pozastaveni hlavniho vlakna - simulace interaktivni prace."
    []
    (Thread/sleep 1000))

(defn -main
    "Tato funkce se spusti automaticky nastrojem Leiningen."
    [& args]
    (println "Start")
    ; vytvorime kanal
    (let [channel (chan)]
        ; poslani zpravy do kanaly (go block bude cekat na precteni)
        (go (>! channel "Hello world #1!"))
        (wait)

        ; precteni zpravy z kanalu
        (go (println (<! channel)))
        (wait)
        
        ; pokus o precteni zpravy z kanalu (ten je prazdny, takze se go block zastavi)
        (go (println (<! channel)))
        (wait)

        ; poslani zpravy do kanalu, na tuto zpravu se jiz netrpelive ceka
        (go (>! channel "Hello world #2!"))
        (wait))

    (println "Finish"))

