(ns htmltest2.core
    (:gen-class))

(require '[hiccup.page :as page])

(defn fact
    [n]
    (apply * (range 1 (inc n))))

(defn html-page
    []
    (page/xhtml
        [:head
            [:title "Hiccup test #2"]
            [:meta {:name "Generator" :content "Clojure"}]
            [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
        ]
        [:body
            [:h1 "Hiccup test #2"]
            [:table
                [:tr [:th "n"] [:th "n!"]]
                (for [n (range 0 20)]
                    [:tr [:td n] [:td (fact n)]])
            ]
        ]))

(defn -main
    [& args]
    (spit "test.html" (html-page)))

