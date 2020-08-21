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

(ns seesaw13.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.color)

(def formular
    (border-panel :hgap 10 :vgap 10 :border 10
                  :north  (button :text "North" :background "#afa")
                  :south  (button :text "South" :background "#aaf")
                  :west   (button :text "West"   :background :yellow)
                  :center (button :text "Center" :background :orange)
                  :east   (button :text "East"   :background :red) 
                  ))

(defn -main
    [& args]
    (-> (frame :title "Border layout test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

