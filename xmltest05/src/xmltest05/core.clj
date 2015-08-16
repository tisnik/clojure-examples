(ns xmltest05.core)

(use '[clojure.xml])
(use '[clojure.xml])
(use '[clojure.pprint])
(use '[clojure.zip])

(defn xmltest05
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")
          zipper     (clojure.zip/xml-zip parsed-xml)]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (println (:tag (clojure.zip/node zipper)))
        (println (:tag (-> zipper
                           clojure.zip/node)))
        (println (:tag (-> zipper
                           clojure.zip/down    ; prvni synovsky uzel
                           clojure.zip/node)))
        (println (:tag (-> zipper
                           clojure.zip/down    ; prvni synovsky uzel
                           clojure.zip/down    ; synovsky uzel prvniho synovskeho uzlu
                           clojure.zip/node)))
        (println (:attrs (-> zipper
                           clojure.zip/down    ; prvni synovsky uzel
                           clojure.zip/right   ; druhy uzel v rade
                           clojure.zip/node)))))

(defn -main
    [& args]
    (xmltest05))

