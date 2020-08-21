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

(ns seesaw3.core
    (:gen-class))

(use 'seesaw.core)

(defn -main
    [& args]
    (-> (frame :title "Hello world!"
               :content (button :text "Click me"))
        (pack!)
        (show!)))

