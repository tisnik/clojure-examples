(ns enlive1.core
    (:gen-class))

(require '[net.cgrand.enlive-html :as html])

(html/deftemplate test-page "test.html"
    [data-for-page]
    [:title] (html/content (:title data-for-page))
    [:h1]    (html/content (:title data-for-page))
    [:div]   (html/content (:paragraph data-for-page))
    )

(def new-data
    {:title "Zcela novy titulek stranky"
     :paragraph "xyzzy"
    })

(defn -main
    [& args]
    (println (reduce str (test-page new-data))))

