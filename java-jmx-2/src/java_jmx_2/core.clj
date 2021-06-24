(ns java-jmx-2.core
  (:gen-class))

(require '[clojure.java.jmx :as jmx])

(defn -main
  [& args]
  (jmx/register-mbean
    (jmx/create-bean
      (ref {:string-attribute "this-is-a-string"}))
    "cz.root.clojure:name=AString")

  ; cekani na klavesu
  (println "Any key to exit...")
  (.read (new java.io.InputStreamReader System/in)))
