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

(ns xmltest03.core)

(use '[clojure.xml])
(use '[clojure.pprint])

(defn print-xml-seq
    [parsed-xml]
    (doseq [item (xml-seq parsed-xml)]
        (println item)))

(defn xmltest03
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (print-xml-seq parsed-xml)))

(defn -main
    [& args]
    (xmltest03))

