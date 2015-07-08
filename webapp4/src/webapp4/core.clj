(ns webapp4.core
    (:gen-class))

(require '[ring.adapter.jetty     :as jetty])
(require '[ring.middleware.params :as params])
(require '[ring.util.response     :as response])

(defn render-html-page
    [x y result]
    (str
    "<html>
        <head>
            <title>Ultimate calculator:</title>
        </head>
        <body>
            <h1>Ultimate calculator:</h1>
            <form method='get' action='/'>
                <input type='text' name='x' size='10' value='" x "'/> ×
                <input type='text' name='y' size='10' value='" y "'/> =
                <input type='text' name='result' size='20' value='" result "' readonly='readonly'/>
                <input type='submit' value='Calculate' />
            </form>
        </body>
     </html>"))

(defn param->number
    "Prevod parametru specifikovaneho v param-name na cislo typu BigDecimal."
    [params param-name]
    (let [param (get params param-name)]
        (try
            (bigdec param)             ; pokus o prevod na BigDecimal
            (catch Exception e nil)))) ; pokud se prevod nepovede, vraci se nil

(defn compute-result
    [x y]
    (if (and x y) (* x y))) ; vetev else neni uvedena -> nil

(defn handler
    [request]
    (let [params (:params request)
          x      (param->number params "x")
          y      (param->number params "y")
          result (compute-result x y)]
    (println params x y result)
    (-> (response/response (render-html-page x y result))
        (response/content-type "text/html; charset=utf-8"))))

(def app
    "Funkce predstavujici kostru webove aplikace."
    (-> handler
        (params/wrap-params)))

(defn -main
    "Spusteni webove aplikace na portu 8080."
    [& args]
    (jetty/run-jetty app {:port 8080}))

