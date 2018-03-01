(ns spec-demo2.core)

(require '[clojure.spec.alpha :as spec])

(defn name?
    [s]
    (and (string? s) (re-matches #"[A-Z][a-z]+" s)))

(defn surname?
    [s]
    (and (string? s) (re-matches #"[A-Z][A-Za-z ]+" s)))

(defn -main
    [& args]
    (spec/def ::id pos-int?)
    (spec/def ::name name?)
    (spec/def ::surname surname?)
    (spec/def ::person? (spec/keys :req-un [::id ::name ::surname]))

    (println "valid?")
    (println "--------------------------------------------------")
    (println (spec/valid? ::person? {:id 10 :name "Rich" :surname "Hickey"}))
    (println (spec/valid? ::person? {:id 10 :name "rich" :surname "Hickey"}))
    (println (spec/valid? ::person? {:id 10 :name "Rich" :surname "hickey"}))
    (println (spec/valid? ::person? {:id -10 :name "Rich" :surname "Hickey"}))
    (println (spec/valid? ::person? {:id -10 :name "rich" :surname "hickey"}))

    (println "\nexplain")
    (println "--------------------------------------------------")
    (println (spec/explain ::person? {:id 10 :name "Rich" :surname "Hickey"}))
    (println)
    (println (spec/explain ::person? {:id 10 :name "rich" :surname "Hickey"}))
    (println)
    (println (spec/explain ::person? {:id 10 :name "Rich" :surname "hickey"}))
    (println)
    (println (spec/explain ::person? {:id -10 :name "Rich" :surname "Hickey"}))
    (println)
    (println (spec/explain ::person? {:id -10 :name "rich" :surname "hickey"}))
    (println))
