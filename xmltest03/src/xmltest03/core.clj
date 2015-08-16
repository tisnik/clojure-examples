(ns xmltest03.core)

(use '[clojure.xml])
(use '[clojure.pprint])

(defn print-xml-seq
    [parsed-xml]
    (doseq [item (xml-seq parsed-xml)]
        (println item)))

(defn xmltest03
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (print-xml-seq parsed-xml)))

(defn -main
    [& args]
    (xmltest03))

