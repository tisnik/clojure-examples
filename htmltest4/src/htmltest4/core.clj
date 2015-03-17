(ns htmltest4.core
    (:gen-class))

(require '[hiccup.page :as page])
(require '[hiccup.form :as form])

(defn fact
    [n]
    (apply * (range 1 (inc n))))

(defn html-page
    []
    (page/xhtml
        [:head
            [:title "Hiccup test #4"]
            [:meta {:name "Generator" :content "Clojure"}]
            [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
        ]
        [:body
            [:h1 "Hiccup test #4"]
            (form/form-to [:get "/"]
                (form/text-field {:size "20"} "n" 10)
                [:br]
                (form/submit-button "Recalculate"))
            [:br]
            [:table {:style "border:2px solid brown;background-color:#ace"}
                [:tr [:th "n"] [:th "n!"]]
                (for [n (range 0 20)]
                    [:tr [:td n] [:td {:style "text-align:right"} (fact n)]])
            ]
        ]))

(defn -main
    [& args]
    (spit "test.html" (html-page)))

