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
(viz/save-graph (keys g) g :filename "g01.png")

; ulozeni grafu i s popisky uzlu
(viz/save-graph (keys g) g :filename "g02.png"
                           :node->descriptor (fn [n] {:label n}))

; graf, kde i uzly existuji ve forme zaznamu
(def g {"Algol" ["K&R C"],
        "K&R C" ["ANSI C"],
        "ANSI C" nil})

; ulozeni grafu i s popisky uzlu
(viz/save-graph (keys g) g :filename "g03.png"
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
(viz/save-graph (keys g) g :filename "g04.png"
                           :node->descriptor (fn [n] {:label n}))

; zmena stylu vykresleni grafu - bez orientovanych hran
(viz/save-graph (keys g) g :filename "g05.png"
                           :node->descriptor (fn [n] {:label n})
                           :directed? nil)

; zmena stylu vykresleni grafu - horizontalne orientovany
(viz/save-graph (keys g) g :filename "g06.png"
                           :node->descriptor (fn [n] {:label n})
                           :vertical? false)

; ulozeni grafu i s popisky uzlu a hran
(viz/save-graph (keys g) g :filename "g07.png"
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
(viz/save-graph (keys g) g :filename "g08.png"
                           :node->descriptor make-node-descriptor
                           :edge->descriptor make-edge-descriptor)

(defn make-node-descriptor-2
  [n]
  {:label n
   :color (if (= n "ANSI C") "darkgreen" "#000000")})

; styly vykresleni uzlu
(viz/save-graph (keys g) g :filename "g09.png"
                           :node->descriptor make-node-descriptor-2
                           :edge->descriptor make-edge-descriptor)

(defn make-edge-descriptor-2
  [n1 n2]
  (let [color (cond (= n1 "ANSI C") "red"
                    (= n2 "ANSI C") "blue"
                    :else "black")]
    {:label (str n1 "&rarr;" n2)
     :color color}))

; styly vykresleni hran
(viz/save-graph (keys g) g :filename "g10.png"
                           :node->descriptor make-node-descriptor-2
                           :edge->descriptor make-edge-descriptor-2)
