(ns webapp2.core
    (:gen-class))

(require '[ring.adapter.jetty :as jetty])
(require '[clojure.pprint     :as pprint])

(defn render-response-body
    [request]
    (with-out-str (pprint/pprint request)))

(defn app
    "Funkce predstavujici kostru webove aplikace."
    [request]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body (render-response-body request)})

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

