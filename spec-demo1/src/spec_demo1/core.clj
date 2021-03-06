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

(ns spec-demo1.core)

(require '[clojure.spec.alpha :as spec])

(defn -main
    [& args]
    (spec/def ::person? (spec/keys :req [::id ::name ::surname]))

    (println "valid?")
    (println "--------------------------------------------------")
    (println (spec/valid? ::person? {:id 1 :name "Name" :surname "Surname"}))
    (println (spec/valid? ::person? {::name "Name" ::surname "Surname"}))
    (println (spec/valid? ::person? {::id 1 ::name "Name" ::surname "Surname"}))
    (println (spec/valid? ::person? {:user/id 1 :user/name "Name" :user/surname "Surname"}))
    (println (spec/valid? ::person? {:spec-demo1.core/id 1 :spec-demo1.core/name "Name" :spec-demo1.core/surname "Surname"}))
    (println (spec/valid? ::person? {:other.namespace/id 1 :other.namespace/name "Name" :other.namespace/surname "Surname"}))

    (println "\nexplain")
    (println "--------------------------------------------------")
    (println (spec/explain ::person? {:id 1 :name "Name" :surname "Surname"}))
    (println)
    (println (spec/explain ::person? {::name "Name" ::surname "Surname"}))
    (println)
    (println (spec/explain ::person? {::id 1 ::name "Name" ::surname "Surname"}))
    (println)
    (println (spec/explain ::person? {:user/id 1 :user/name "Name" :user/surname "Surname"}))
    (println)
    (println (spec/explain ::person? {:spec-demo1.core/id 1 :spec-demo1.core/name "Name" :spec-demo1.core/surname "Surname"}))
    (println)
    (println (spec/explain ::person? {:other.namespace/id 1 :other.namespace/name "Name" :other.namespace/surname "Surname"})))
