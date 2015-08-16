(ns xmltest01.core)

(use '[clojure.xml])
(use '[clojure.pprint])

(defn xmltest01
    []
    (let [parsed-xml (clojure.xml/parse "test.xml")]
        (clojure.pprint/pprint parsed-xml)))

(defn -main
    [& args]
    (xmltest01))
