(ns htmltest1.core
    (:gen-class))

(require '[hiccup.page :as page])

(defn html-page
    []
    (page/xhtml
        [:head
            [:title "Hiccup test #1"]
            [:meta {:name "Generator" :content "Clojure"}]
            [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
        ]
        [:body
            [:h1 "Hiccup test #1"]
            [:div "Hello world!"]
        ]))

(defn -main
    [& args]
    (spit "test.html" (html-page)))

