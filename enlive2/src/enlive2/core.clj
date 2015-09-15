(ns enlive2.core
    (:gen-class))

(require '[net.cgrand.enlive-html :as html])

(html/deftemplate test-page "test.html"
    [data-for-page]
    [:title] (html/content (:title data-for-page))
    [:h1]    (html/content (:title data-for-page))
    [:div#paragraph1]   (html/content (:paragraph1 data-for-page))
    [:div#paragraph2]   (html/content (:paragraph2 data-for-page))
    [:div.paragraphs]   (html/content (:paragraphs data-for-page))
    )

(def new-data
    {:title "Zcela novy titulek stranky"
     :paragraph1 "xyzzy"
     :paragraph2 ""
     :paragraphs "42"
    })

(defn -main
    [& args]
    (println (reduce str (test-page new-data))))

