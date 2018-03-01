(ns spec-demo3.core)

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

(defn checked-get-name
    [person]
    {:pre  [(spec/valid? ::person? person)]
     :post [(spec/valid? ::name %)]}
    (:name person))

(defn -main
    [& args]
    (println "get-name")
    (println (get-name {:id 10 :name "Rich" :surname "Hickey"}))
    (println (get-name {:id 10 :name "rich" :surname "Hickey"}))
    (println (get-name {:id 10 :name "Rich" :surname "hickey"}))
    (println (get-name {:id -10 :name "Rich" :surname "Hickey"}))
    (println (get-name {:id -10 :name "rich" :surname "hickey"}))
    (println)
    (println "checked-get-name")
    (println (checked-get-name {:id 10 :name "Rich" :surname "Hickey"}))
    (println (checked-get-name {:id 10 :name "rich" :surname "Hickey"}))
    (println (checked-get-name {:id 10 :name "Rich" :surname "hickey"}))
    (println (checked-get-name {:id -10 :name "Rich" :surname "Hickey"}))
    (println (checked-get-name {:id -10 :name "rich" :surname "hickey"})))
