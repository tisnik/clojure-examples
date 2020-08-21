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

(ns seesaw4.core
    (:gen-class))

(use 'seesaw.core)

(defn -main
    [& args]
    (let [btn (button :text "Click me"
                      :background "#afa")]
         (listen btn :mouse-clicked (fn [e] (println "Very well"))
                     :mouse-entered (fn [e] (println "Mouse caught"))
                     :mouse-exited  (fn [e] (println "Mouse escaped")))
         (-> (frame :title "Hello world!"
                    :on-close :exit
                    :content btn)
             (pack!)
             (show!))))

