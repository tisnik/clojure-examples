(ns spec-demo7.core)

(require '[clojure.spec.alpha :as spec])

(spec/def ::unique-id (spec/or :name string? :id pos-int?))


(defn hr
    []
    (println "-------------------------------"))

(defn print-title
    [title]
    (hr)
    (println title)
    (hr)
    (println))


(defn validate-unique-id
    [id]
    (println "id=" id)
    (println (if (spec/valid? ::unique-id id) "valid" "invalid"))
    (println))


(defn explain-unique-id-validation
    [id]
    (println "id=" id)
    (println (spec/explain ::unique-id id))
    (println))


(defn -main
    [& args]
    (let [ids [nil [] 1/2 0.5 -1 "42" 0 "" 1 1000 10000 1000000000 10000000000 "*?"]]
         (print-title "Validation")

         (doseq [id ids]
             (validate-unique-id id))

         (print-title "Explain validation failures")

         (doseq [id ids]
             (explain-unique-id-validation id))))
