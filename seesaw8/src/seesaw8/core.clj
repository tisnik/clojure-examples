;
;  (C) Copyright 2015, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

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

