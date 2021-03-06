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

(ns webapp13.core
  (:gen-class))

(require '[ring.adapter.jetty     :as jetty])
(require '[ring.middleware.params :as http-params])
(require '[ring.util.response     :as response])

(require '[hiccup.page :as page])
(require '[hiccup.form :as form])

(defn fact
    [n]
    (apply * (range 1M (inc n))))

(defn html-page
    [max-n]
    (page/xhtml
        [:head
            [:title "Hiccup test #4"]
            [:meta {:name "Generator" :content "Clojure"}]
            [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
        ]
        [:body
            [:h1 "Hiccup test #4"]
            (form/form-to [:get "/"]
                (form/text-field {:size "20"} "max-n" max-n)
                [:br]
                (form/submit-button "Recalculate"))
            [:br]
            [:table {:style "border:2px solid brown;background-color:#ace"}
                [:tr [:th "n"] [:th "n!"]]
                (for [n (range 0M (inc max-n))]
                    [:tr [:td n] [:td {:style "text-align:right"} (fact n)]])
            ]
        ]))

(defn param->number
    "Prevod parametru specifikovaneho v param-name na cislo typu BigDecimal."
    [params param-name]
    (let [param (get params param-name)]
        (try
            (bigdec param)             ; pokus o prevod na BigDecimal
            (catch Exception e nil)))) ; pokud se prevod nepovede, vraci se nil

(defn handler
    "Zpracovani pozadavku."
    [request]
    (let [params (:params request)
          max-n  (param->number params "max-n")]
        (println "Params: " params)
        (println "max-n:  " max-n)
        (-> (response/response (html-page (if max-n max-n 10M)))
            (response/content-type "text/html; charset=utf-8"))))

(def app
    "Datova struktura predstavujici kostru webove aplikace."
    (-> handler
        http-params/wrap-params))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

