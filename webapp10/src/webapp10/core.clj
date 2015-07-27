(ns webapp10.core
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
    (let [params      (:params  request)
          session     (:session request)
          counter     (:counter session 0)]
        (println "Params:  " params)
        (println "Session: " session)
        (println "Counter: " counter)
        (-> (response/response html-page)
            (response/content-type "text/html; charset=utf-8")
            (assoc :session {:counter (inc counter)}))))

(def app
    "Datova struktra predstavujici kostru webove aplikace."
    (-> handler
        http-session/wrap-session
        http-params/wrap-params))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

