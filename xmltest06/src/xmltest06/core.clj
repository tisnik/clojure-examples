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

(ns xmltest06.core)

(use '[clojure.xml])
(use '[clojure.pprint])
(use '[clojure.zip])

(defn xmltest06
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")
          zipper     (clojure.zip/xml-zip parsed-xml)]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (println (-> zipper
                     clojure.zip/down   ; prvni synovsky uzel: <second>
                     clojure.zip/right  ; druhy synovsky uzel: (taktez <second>)
                     clojure.zip/down   ; poduzel: <third>
                     clojure.zip/down   ; poduzel: <fourth>
                     clojure.zip/node
                     :content           ; nahrada za get
                     first))            ; prvni (a jediny prvek vektoru)
                     ))

(defn -main
    [& args]
    (xmltest06))

