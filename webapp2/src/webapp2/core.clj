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

