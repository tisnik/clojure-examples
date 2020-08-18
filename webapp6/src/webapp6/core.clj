;
;  (C) Copyright 2015, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns webapp6.core
    (:gen-class))

(require '[ring.adapter.jetty     :as jetty])
(require '[ring.util.response     :as response])

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
    (-> (response/response html-page)
        (response/content-type "text/html; charset=utf-8")))

(def app
    "Datova struktura predstavujici kostru webove aplikace."
    (-> handler))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

