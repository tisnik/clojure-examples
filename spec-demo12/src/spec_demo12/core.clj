(ns spec-demo12.core)

(require '[clojure.pprint :as pprint])
(require '[clojure.spec.alpha :as spec])
(require '[clojure.spec.gen.alpha :as gen])

(spec/def ::user-id      (spec/and pos-int? #(>= % 1000) #(< % Integer/MAX_VALUE)))
(spec/def ::unique-id    (spec/or :name string? :id pos-int?))

(spec/def ::comment-ver1 (spec/or :filled string? :empty nil?))
(spec/def ::comment-ver2 (spec/nilable string?))

(spec/def ::byte         (spec/and nat-int? #(<= % 255)))
(spec/def ::ip-address   (spec/tuple ::byte ::byte ::byte ::byte))


(defn generate-test-data
    [validation-key]
    (println "validation-key" validation-key)
    (doseq [i (range 10)]
        (println (str "#" i ": ") (gen/generate (spec/gen validation-key))))
    (println "\n\n\n"))


(defn -main
    [& args]
    (let [validation-keys [::user-id ::unique-id ::comment-ver1 ::comment-ver2 ::ip-address]]
        (doseq [validation-key validation-keys]
            (generate-test-data validation-key))))


