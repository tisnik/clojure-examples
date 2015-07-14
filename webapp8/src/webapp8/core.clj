(ns webapp8.core
    (:gen-class))

(require '[ring.adapter.jetty      :as jetty])
(require '[ring.middleware.params  :as http-params])
(require '[ring.middleware.session :as http-session])
(require '[ring.util.response      :as response])

(def html-page
"    <html>
         <head>
             <title>Powered by Ring!</title>
         </head>
         <body>
             <h1>Powered by Ring!</h1>
         </body>
     </html>
")

(defn handler
    "Zpracovani pozadavku."
    [request]
    (let [params  (:params request)
          session (:session request)]
        (println "Params:  " params)
        (println "Session: " session))
    (-> (response/response html-page)
        (response/content-type "text/html; charset=utf-8")))

(def app
    "Datova struktura predstavujici kostru webove aplikace."
    (-> handler
        http-session/wrap-session
        http-params/wrap-params))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

