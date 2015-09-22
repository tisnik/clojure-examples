(ns enlive7.core
    (:use net.cgrand.enlive-html))

(defn load-html-page
    "Nacteni testovaci stranky umistene v podadresari './resources'."
    []
    (html-resource "test.html"))

(defn print-tag-content
    "Vypis textoveho obsahu vybraneho tagu."
    [tag]
    (-> tag
        :content
        clojure.string/join
        println))

(defn print-title
    "Vyber titulku stranky, tj. textu uzavreneho do <title></title>."
    [html-page]
    (-> html-page
        (select [:head :title])
        first
        print-tag-content))

(defn print-h1
    "Vyber n-teho nadpisu, kde cislovani zacina od jednicky."
    [html-page n]
    (-> html-page
        (select [:body :h1])
        (nth (dec n))
        print-tag-content))

(defn -main
    "Vstupni bod do aplikace."
    [ & args]
    (doto (load-html-page)
          (print-title)
          (print-h1 1)
          (print-h1 2)
          (print-h1 3)))

