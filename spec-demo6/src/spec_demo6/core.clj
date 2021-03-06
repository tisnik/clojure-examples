;
;  (C) Copyright 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns spec-demo6.core)

(require '[clojure.spec.alpha :as spec])

(spec/def ::user-id (spec/and pos-int? #(>= % 1000) #(< % Integer/MAX_VALUE)))


(defn hr
    []
    (println "-------------------------------"))

(defn print-title
    [title]
    (hr)
    (println title)
    (hr)
    (println))


(defn validate-user-id
    [id]
    (println "id=" id)
    (println (if (spec/valid? ::user-id id) "valid" "invalid"))
    (println))


(defn explain-user-id-validation
    [id]
    (println "id=" id)
    (println (spec/explain ::user-id id))
    (println))


(defn -main
    [& args]
    (let [ids [-1 0 1 1000 10000 1000000000 10000000000]]
         (print-title "Validation")

         (doseq [id ids]
             (validate-user-id id))

         (print-title "Explain validation failures")

         (doseq [id ids]
             (explain-user-id-validation id))))
