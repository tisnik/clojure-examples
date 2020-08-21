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

(ns seesaw6.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.color)

(def formular
    (grid-panel :columns 3
                :rows 3
                :items ["Color test"
                        (button :text ":background :red"    :background :red)
                        (button :text ":background :yellow" :background :yellow)
                        (button :text ":background :orange" :background :orange)
                        (button :text ":background #ff8080" :background "#ff8080")
                        (button :text ":background #8080ff" :background "#8080ff")
                        (button :text ":background #8f8"    :background "#8f8")
                        (button :text ":background #ff8"    :background "#ff8")
                        (button :text ":foreground :orange" :foreground :orange)
                        ]))

(defn -main
    [& args]
    (-> (frame :title "Color test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

