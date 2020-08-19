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

(ns xmltest05.core)

(use '[clojure.xml])
(use '[clojure.pprint])
(use '[clojure.zip])

(defn xmltest05
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")
          zipper     (clojure.zip/xml-zip parsed-xml)]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (println (:tag (clojure.zip/node zipper)))
        (println (:tag (-> zipper
                           clojure.zip/node)))
        (println (:tag (-> zipper
                           clojure.zip/down    ; prvni synovsky uzel
                           clojure.zip/node)))
        (println (:tag (-> zipper
                           clojure.zip/down    ; prvni synovsky uzel
                           clojure.zip/down    ; synovsky uzel prvniho synovskeho uzlu
                           clojure.zip/node)))
        (println (:attrs (-> zipper
                           clojure.zip/down    ; prvni synovsky uzel
                           clojure.zip/right   ; druhy uzel v rade
                           clojure.zip/node)))))

(defn -main
    [& args]
    (xmltest05))

