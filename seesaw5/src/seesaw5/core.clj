(ns seesaw5.core
    (:gen-class))

(use 'seesaw.core)

(def formular
    (grid-panel :columns 2
                :rows 2
                :items ["Label1"
                        (button :text "Click me")
                        "Label2"]))

(defn -main
    [& args]
    (-> (frame :title "Grid-panel test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

