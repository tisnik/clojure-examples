; nejprve nacteme vsechny potrebne balicky
(require '[rhizome.viz :as viz])
(require '[rhizome.dot :as dot])

(def g {"Algol" ["K&R C"],
        "K&R C" ["ANSI C"]})

; pouzijeme funkci save-graph pro ulozeni grafu na disk ve formatu PNG
(doc viz/save-graph)

; existuje i pomocna funkce save-image
(doc viz/save-image)

; ulozeni grafu bez popisku uzlu
(viz/save-graph (keys g) g :filename "g1.png")

; ulozeni grafu i s popisky uzlu
(viz/save-graph (keys g) g :filename "g2.png"
                           :node->descriptor (fn [n] {:label n}))

; graf, kde i uzly existuji ve forme zaznamu
(def g {"Algol" ["K&R C"],
        "K&R C" ["ANSI C"],
        "ANSI C" nil})

; ulozeni grafu i s popisky uzlu
(viz/save-graph (keys g) g :filename "g3.png"
                           :node->descriptor (fn [n] {:label n}))

; slozitejsi graf, kde maji uzly vice potomku
(def g {"Algol" ["K&R C", "Pascal", "Modula"],
        "K&R C" ["ANSI C", "C with classes"],
        "C with classes" ["C++"],
        "ANSI C" ["C89"],
        "C89" ["C99"],
        "Pascal" nil,
        "Modula" nil,
        "C++" nil,
        "C99" nil})

; ulozeni grafu i s popisky uzlu
(viz/save-graph (keys g) g :filename "g4.png"
                           :node->descriptor (fn [n] {:label n}))

; zmena stylu vykresleni grafu - bez orientovanych hran
(viz/save-graph (keys g) g :filename "g5.png"
                           :node->descriptor (fn [n] {:label n})
                           :directed? nil)

; zmena stylu vykresleni grafu - horizontalne orientovany
(viz/save-graph (keys g) g :filename "g6.png"
                           :node->descriptor (fn [n] {:label n})
                           :vertical? false)

; ulozeni grafu i s popisky uzlu a hran
(viz/save-graph (keys g) g :filename "g7.png"
                           :node->descriptor (fn [n] {:label n})
                           :edge->descriptor (fn [n1 n2] {:label (str n1 "&rarr;" n2)}))

(defn make-node-descriptor
  [n]
  {:label n})

(defn make-edge-descriptor
  [n1 n2]
  {:label (str n1 "&rarr;" n2)})

; dtto ovsem bez pouziti anonymnich funkci
; ulozeni grafu i s popisky uzlu a hran
(viz/save-graph (keys g) g :filename "g8.png"
                           :node->descriptor make-node-descriptor
                           :edge->descriptor make-edge-descriptor)

