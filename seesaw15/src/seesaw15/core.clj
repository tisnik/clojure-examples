(ns seesaw15.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.color)

(def formular
    (vertical-panel :border 10
                :items [(button :text ":background :red"    :background :red)
                        (button :text ":background :yellow" :background :yellow)
                        (button :text ":background :orange" :background :orange)
                        (button :text ":background #ff8080" :background "#ff8080")
                        (button :text ":background #8080ff" :background "#8080ff")
                        (button :text ":background #8f8"    :background "#8f8")
                        ]))

(defn -main
    [& args]
    (-> (frame :title "Vertical panel layout test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))
