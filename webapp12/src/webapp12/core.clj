(ns webapp12.core
    (:gen-class))

(require '[ring.adapter.jetty      :as jetty])
(require '[ring.middleware.params  :as http-params])
(require '[ring.util.response      :as http-response])
(require '[ring.middleware.cookies :as cookies])

(defn create-html-page
    "Vygenerovani HTML stranky."
    [user-name]
    (str
"    <html>
         <head>
             <title>Powered by Ring!</title>
         </head>
         <body>
             <h1>Powered by Ring!</h1>
             <form method='post' action='/'>
                 User name: <input type='text' name='user-name' value='" user-name "'/><br />
                            <input type='submit' value='Remember me' />
             </form>
         </body>
     </html>
"))

(defn generate-response
    "Vytvoreni odpovedi."
    [user-name]
     (let [html-output (create-html-page user-name)]
        (if user-name
            (-> (http-response/response html-output)
                (http-response/set-cookie :user-name user-name {:max-age 36000000})
                (http-response/content-type "text/html"))
            (-> (http-response/response html-output)
                (http-response/content-type "text/html")))))

(defn handler
    "Zpracovani pozadavku."
    [request]
    (let [params        (:params  request)
          cookies       (:cookies request)
          new-user-name (get params "user-name")
          old-user-name (get (get cookies "user-name") :value)
          user-name     (or new-user-name old-user-name)]
        (println "New user name " new-user-name)
        (println "Old user name " old-user-name)
        (println "Incoming cookies: " cookies)
        (let [response (generate-response user-name)]
              (println "Outgoing cookies: " (get response :cookies))
              response)))

(def app
    "Datova struktra predstavujici kostru webove aplikace."
    (-> handler
        cookies/wrap-cookies
        http-params/wrap-params))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

