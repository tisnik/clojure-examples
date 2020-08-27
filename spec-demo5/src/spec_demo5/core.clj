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

(ns spec-demo5.core)

(require '[clojure.spec.alpha :as spec])

(defn name?
    [s]
    (and (string? s) (re-matches #"[A-Z][a-z]+" s)))

(defn surname?
    [s]
    (and (string? s) (re-matches #"[A-Z][A-Za-z ]+" s)))

(defn email?
    [s]
    (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
        (and (string? s) (re-matches pattern s))))

(spec/def ::id      pos-int?)
(spec/def ::phone   pos-int?)
(spec/def ::name    name?)
(spec/def ::surname surname?)
(spec/def ::e-mail   email?)
(spec/def ::person? (spec/keys :req-un [::id ::name ::surname] :opt-un [::phone ::e-mail]))

(defn -main
    "I don't do a whole lot ... yet."
    [& args]
    (let [persons [ {:id 10 :name "Rich" :surname "Hickey"}
                    {:id 10 :name "rich" :surname "Hickey"}
                    {:id 10 :name "Rich" :surname "hickey"}
                    {:id -10 :name "Rich" :surname "Hickey"}
                    {:id -10 :name "rich" :surname "hickey"}
                    {:id 10 :name "Rich" :surname "Hickey" :e-mail "rich@somewhere.org"}
                    {:id 10 :name "Rich" :surname "Hickey" :e-mail "wrong"}
                    {:id 10 :name "Rich" :surname "Hickey" :phone 123456789}
                    {:id 10 :name "Rich" :surname "Hickey" :phone nil}
                    {:id 10 :name "Rich" :surname "Hickey" :phone 987654321 :e-mail "rich@somewhere.org"}
                    {:id -10 :name "rich" :surname "" :phone -5 :e-mail "wrong"}]]
        (println "valid?")
        (println "-------------------------------")

        (doseq [person persons]
            (println person)
            (println (if (spec/valid? ::person? person) "yes\n" "no\n")))

        (println "\n\n\n")
        (println "explain")
        (println "-------------------------------")

        (doseq [person persons]
            (println person)
            (println (spec/explain ::person? person))

            (println))))
