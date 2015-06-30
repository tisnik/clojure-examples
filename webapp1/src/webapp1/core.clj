(ns webapp1.core
    (:gen-class))

(require '[ring.adapter.jetty :as jetty])

(defn app
    "Funkce predstavujici kostru webove aplikace."
    [request]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body "Hello World"})

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

