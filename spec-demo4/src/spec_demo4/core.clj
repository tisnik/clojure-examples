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

(ns spec-demo4.core)

(require '[clojure.spec.alpha :as spec])

(defn name?
    [s]
    (and (string? s) (re-matches #"[A-Z][a-z]+" s)))

(defn surname?
    [s]
    (and (string? s) (re-matches #"[A-Z][A-Za-z ]+" s)))

(spec/def ::id pos-int?)
(spec/def ::name name?)
(spec/def ::surname surname?)
(spec/def ::person? (spec/keys :req-un [::id ::name ::surname]))

(defn get-name
    [person]
    (:name person))

(defn broken-checked-get-name
    [person]
    {:pre  [(spec/valid? ::person? person)]
     :post [(spec/valid? ::name %)]}
    (str "name: " (:name person)))

(defn -main
    [& args]
    (println "get-name")
    (println (get-name {:id 10 :name "Rich" :surname "Hickey"}))
    (println (get-name {:id 10 :name "rich" :surname "Hickey"}))
    (println (get-name {:id 10 :name "Rich" :surname "hickey"}))
    (println (get-name {:id -10 :name "Rich" :surname "Hickey"}))
    (println (get-name {:id -10 :name "rich" :surname "hickey"}))
    (println)
    (println "broken-checked-get-name")
    (println (broken-checked-get-name {:id 10 :name "Rich" :surname "Hickey"})))
