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

(ns seesaw2.core
    (:gen-class))

(use 'seesaw.core)

(defn -main
    [& args]
    (let [main-frame (frame :title "Hello world!")
          btn        (button :text "Click Me")]
          (-> main-frame
              (config! :content btn)
              (pack!)
              (show!))))

