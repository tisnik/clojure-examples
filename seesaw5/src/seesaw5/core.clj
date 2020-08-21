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

