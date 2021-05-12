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
        "ANSI C" nil,
        "Pascal" nil,
        "Modula" nil,
        "C with classes" nil})

; ulozeni grafu i s popisky uzlu
(viz/save-graph (keys g) g :filename "g4.png"
                           :node->descriptor (fn [n] {:label n}))

