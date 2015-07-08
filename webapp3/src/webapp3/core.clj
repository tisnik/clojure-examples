(ns webapp3.core
    (:gen-class))

(require '[ring.adapter.jetty     :as jetty])
(require '[ring.middleware.params :as params])
(require '[ring.util.response     :as response])

(def html-page
    "<html>
        <head>
            <title>Powered by Ring!</title>
        </head>
        <body>
            <h1>Powered by Ring!</h1>
        </body>
     </html>")

(defn handler
    [request]
    (-> (response/response html-page)
        (response/content-type "text/html; charset=utf-8")))

(def app
    "Funkce predstavujici kostru webove aplikace."
    (-> handler
        (params/wrap-params)))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

