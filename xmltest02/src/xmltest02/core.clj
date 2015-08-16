(ns xmltest02.core)

(use '[clojure.xml])
(use '[clojure.pprint])

(defn get-attributes-v1
    [parsed-xml]
    (:attrs (first (:content (first (:content parsed-xml))))))

(defn get-attributes-v2
    [parsed-xml]
    (-> parsed-xml
        :content
        first
        :content
        first
        :attrs))

(defn xmltest02
    []
    (let [parsed-xml (clojure.xml/parse "test.xml")]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (println (get-attributes-v1 parsed-xml))
        (println (get-attributes-v2 parsed-xml))))

(defn -main
    [& args]
    (xmltest02))

