(ns enlive5.core
    (:use net.cgrand.enlive-html))

(defn load-html-page
    "Nacteni testovaci stranky umistene v podadresari './resources'."
    []
    (html-resource "test.html"))

(defn print-tag-content
    "Vypis textoveho obsahu vybraneho tagu."
    [tag]
    (println (clojure.string/join (get tag :content))))

(defn print-title
    "Vyber titulku stranky, tj. textu uzavreneho do <title></title>."
    [html-page]
    (print-tag-content (first (select html-page [:head :title]))))

(defn print-h1
    "Vyber n-teho nadpisu, kde cislovani zacina od jednicky."
    [html-page n]
    (print-tag-content (nth (select html-page [:body :h1]) (dec n))))

(defn -main
    "Vstupni bod do aplikace."
    [& args]
    (let [html-page (load-html-page)]
          (print-title html-page)
          (print-h1 html-page 1)
          (print-h1 html-page 2)
          (print-h1 html-page 3)))

