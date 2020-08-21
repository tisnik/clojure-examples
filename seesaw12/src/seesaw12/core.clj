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

