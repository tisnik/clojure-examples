(ns seesaw8.core
    (:gen-class))

(require ['seesaw.core :as 'core])
(require ['seesaw.bind :as 'bind])

(def slider-control
    (core/slider :min 0 :max 100 :value 50))

(def slider-atom-value (atom 50))

(bind/bind slider-control
      slider-atom-value
      slider-control)

(def print-button
    (core/button :text "Print value"))

(core/listen print-button
             :mouse-clicked (fn [e] (println @slider-atom-value)))

(def formular
    (core/grid-panel :columns 2
                :rows 2
                :items ["Slider test"
                        slider-control
                        ""
                        print-button
                        ]))

(defn -main
    [& args]
    (-> (core/frame :title "Slider test"
                    :on-close :exit
                    :content formular)
        (core/pack!)
        (core/show!)))

