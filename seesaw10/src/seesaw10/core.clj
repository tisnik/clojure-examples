(ns seesaw10.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.color)

(defn button-clicked-event
    [e]
    (alert (str "Button clicked!")))

(def formular
    (grid-panel :columns 2
                :rows 3
                :items [(button :text ":background :red"
                                :background :red
                                :listen [:action button-clicked-event])
                        (button :text ":background :yellow"
                                :background :yellow
                                :listen [:action button-clicked-event])
                        (button :text ":background :orange"
                                :background :orange
                                :listen [:action button-clicked-event])
                        (button :text ":background #ff8080"
                                :background "#ff8080"
                                :listen [:action button-clicked-event])
                        (button :text ":background #8080ff"
                                :background "#8080ff"
                                :listen [:action button-clicked-event])
                        (button :text ":background #8f8"
                                :background "#8f8"
                                :listen [:action button-clicked-event])
                        ]))

(defn -main
    [& args]
    (-> (frame :title "Color test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

