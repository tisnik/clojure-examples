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

(ns seesaw7.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.font)

(def formular
    (grid-panel :columns 3
                :rows 3
                :items ["Font test"
                        (button :text ":font ARIAL-12"
                                :font "ARIAL-12")
                        (button :text ":font ARIAL-BOLD-18"
                                :font "ARIAL-BOLD-18")
                        (button :text ":font ARIAL-ITALIC-20"
                                :font "ARIAL-ITALIC-20")
                        (button :text "{:name \"ARIAL\" :style :bold :size 16}"
                                :font {:name "ARIAL" :style :bold :size 16})
                        (button :text "{:name \"DejaVu Sans Mono\" :size 16}"
                                :font {:name "DejaVu Sans Mono" :size 16})
                        (button :text "{:name \"Liberation Serif\" :style :bold :size 16}"
                                :font {:name "Liberation Serif" :style :bold :size 16})
                        (button :text "{:name \"Liberation Serif\" :style :italic :size 16}"
                                :font {:name "Liberation Serif" :style :italic :size 16})
                        (button :text "{:name \"Liberation Serif\" :style :italic :size 16}"
                                :font {:name "Liberation Serif" :style #{:bold :italic} :size 16})
                        ]))

(defn -main
    [& args]
    (-> (frame :title "Font test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

