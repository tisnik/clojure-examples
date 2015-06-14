(ns seesaw12.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.color)

(def formular
    (flow-panel :hgap 10 :vgap 10 :border 10 :align :center
                :items [(button :text ":background :red"    :background :red)
                        (button :text ":background :yellow" :background :yellow)
                        (button :text ":background :orange" :background :orange)
                        (button :text ":background #ff8080" :background "#ff8080")
                        (button :text ":background #8080ff" :background "#8080ff")
                        (button :text ":background #8f8"    :background "#8f8")
                        ]))

(defn -main
    [& args]
    (-> (frame :title "Flow layout test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

