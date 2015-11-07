(ns test-hello.core
  (:gen-class))

(require '[clj-hello.clj-hello :as hello])

(defn -main
    "Entry point to this app"
    [& args]
    (hello/say-hello))

