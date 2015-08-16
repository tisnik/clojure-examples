(ns xmltest04.core)

(use '[clojure.xml])
(use '[clojure.pprint])


(defn filter-seq-1
    [parsed-xml]
    (for [item (xml-seq parsed-xml)
                :when (= :second (:tag item))]
             (:attrs item)))

(defn filter-seq-2
    [parsed-xml]
    (for [item (xml-seq parsed-xml)
                :when (= :fourth (:tag item))]
             (:content item)))

(defn xmltest04
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (clojure.pprint/pprint (filter-seq-1 parsed-xml))
        (println "----------------------------------")
        (clojure.pprint/pprint (filter-seq-2 parsed-xml))))

(defn -main
    [& args]
    (xmltest04))

