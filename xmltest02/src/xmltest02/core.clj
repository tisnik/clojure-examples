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

(ns xmltest02.core)

(use '[clojure.xml])
(use '[clojure.pprint])

(defn get-attributes-v1
    [parsed-xml]
    (:attrs (first (:content (first (:content parsed-xml))))))

(defn get-attributes-v2
    [parsed-xml]
    (-> parsed-xml
        :content
        first
        :content
        first
        :attrs))

(defn xmltest02
    []
    (let [parsed-xml (clojure.xml/parse "test.xml")]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (println (get-attributes-v1 parsed-xml))
        (println (get-attributes-v2 parsed-xml))))

(defn -main
    [& args]
    (xmltest02))

