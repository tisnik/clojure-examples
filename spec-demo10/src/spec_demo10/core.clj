(ns spec-demo10.core)

(require '[clojure.pprint :as pprint])
(require '[clojure.spec.alpha :as spec])

(spec/def ::user-id   (spec/and pos-int? #(>= % 1000) #(< % Integer/MAX_VALUE)))
(spec/def ::unique-id (spec/or :name string? :id pos-int?))

(spec/def ::comment-ver1 (spec/or :filled string? :empty nil?))
(spec/def ::comment-ver2 (spec/nilable string?))


(defn explain-validation->data
    [validation-key value]
    (println "validation-key" validation-key)
    (println "value" value)
    (-> (spec/explain-data validation-key value) (pprint/pprint))
    (println))


(defn -main
    [& args]

    (let [ids [nil [] 1/2 0.5 1 10000000000 "*?"]]
         (doseq [id ids]
             (explain-validation->data ::user-id id)))

    (let [ids [nil [] 1/2 0.5 -1]]
         (doseq [id ids]
             (explain-validation->data ::unique-id id)))

    (let [comments [42 [] {} 1/2]]
         (doseq [comment-value comments]
             (explain-validation->data ::comment-ver1 comment-value)))

    (let [comments [42 [] {} 1/2]]
         (doseq [comment-value comments]
             (explain-validation->data ::comment-ver2 comment-value))))
