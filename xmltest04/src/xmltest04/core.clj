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

(ns xmltest04.core)

(use '[clojure.xml])
(use '[clojure.pprint])


(defn filter-seq-1
    [parsed-xml]
    (for [item (xml-seq parsed-xml)
                :when (= :second (:tag item))]
             (:attrs item)))

(defn filter-seq-2
    [parsed-xml]
    (for [item (xml-seq parsed-xml)
                :when (= :fourth (:tag item))]
             (:content item)))

(defn xmltest04
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (clojure.pprint/pprint (filter-seq-1 parsed-xml))
        (println "----------------------------------")
        (clojure.pprint/pprint (filter-seq-2 parsed-xml))))

(defn -main
    [& args]
    (xmltest04))

