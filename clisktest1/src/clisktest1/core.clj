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

