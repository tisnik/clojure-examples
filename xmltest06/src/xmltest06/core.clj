(ns xmltest06.core)

(use '[clojure.xml])
(use '[clojure.pprint])
(use '[clojure.zip])

(defn xmltest06
    []
    (let [parsed-xml (clojure.xml/parse "nested.xml")
          zipper     (clojure.zip/xml-zip parsed-xml)]
        (clojure.pprint/pprint parsed-xml)
        (println "----------------------------------")
        (println (-> zipper
                     clojure.zip/down   ; prvni synovsky uzel: <second>
                     clojure.zip/right  ; druhy synovsky uzel: (taktez <second>)
                     clojure.zip/down   ; poduzel: <third>
                     clojure.zip/down   ; poduzel: <fourth>
                     clojure.zip/node
                     :content           ; nahrada za get
                     first))            ; prvni (a jediny prvek vektoru)
                     ))

(defn -main
    [& args]
    (xmltest06))

