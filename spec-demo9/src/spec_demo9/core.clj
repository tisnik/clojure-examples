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

(ns spec-demo9.core)

(require '[clojure.spec.alpha :as spec])

(spec/def ::comment (spec/nilable string?))


(defn hr
    []
    (println "-------------------------------"))

(defn print-title
    [title]
    (hr)
    (println title)
    (hr)
    (println))


(defn validate-comment
    [comment-value]
    (println "comment=" comment-value)
    (println (if (spec/valid? ::comment comment-value) "valid" "invalid"))
    (println))


(defn explain-comment-validation
    [comment-value]
    (println "comment-value" comment-value)
    (println (spec/explain ::comment comment-value))
    (println))


(defn -main
    [& args]
    (let [comments [nil 42 [] "text" "text\non\nfour\nlines"]]
         (print-title "Validation")

         (doseq [comment-value comments]
             (validate-comment comment-value))

         (print-title "Explain validation failures")

         (doseq [comment-value comments]
             (explain-comment-validation comment-value))))
