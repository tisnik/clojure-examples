(ns enlive8.core
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

(defn print-first-paragraph
    "Vyber *obsahu* prvniho odstavce."
    [html-page]
    (-> html-page
        (select [:div :> text-node])
        first
        println))

(defn print-generator
    "Vyber a vypis generatoru nacteneho z metainformaci."
    [html-page]
    (-> html-page
        (select [(attr= :name "Generator")])
        first
        :attrs
        :content
        println))

(defn print-meta-tag
    [tag]
    (let [attrs (:attrs tag)]
        (println (:name attrs) " -> " (:content attrs))))

(defn print-all-metatags
    "Vyber a vypis vsech metainformaci."
    [html-page]
    (doseq [tag (select html-page [(attr? :name)])]
        (print-meta-tag tag)))

(defn -main
    "Vstupni bod do aplikace."
    [ & args]
    (doto (load-html-page)
          (print-title)
          (print-h1 1)
          (print-h1 2)
          (print-h1 3)
          (print-first-paragraph)
          (print-generator)
          (print-all-metatags)))

