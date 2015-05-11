(ns clisktest3.core
    (:gen-class)
    (:use clisk.live))

(import java.io.File)
(import javax.imageio.ImageIO)

(defn write-image
    "Uložení rastrového obrázku typu BufferedImage do souboru."
    [image file-name]
    (ImageIO/write image "png" (File. file-name)))

(defn write-pattern
    "Vytvoření rastrového obrázku na základě předaného patternu."
    [pattern file-name]
    (write-image (image pattern) file-name))

(defn predefined-patterns-test-
    "Otestování funkcí pro vytváření vzorků/textur 2D obrázků."
    []
    (write-pattern (checker black white)             "pattern_0.png")
    (write-pattern (scale 1/4 (checker black white)) "pattern_1.png")
    (write-pattern turbulence                        "pattern_2.png")
    (write-pattern (scale 1/10 perlin-noise)         "pattern_3.png")
    (write-pattern (scale 1/40 perlin-noise)         "pattern_4.png")
    (write-pattern (scale 1/10 perlin-snoise)        "pattern_5.png")
    (write-pattern (scale 1/10 simplex-noise)        "pattern_6.png")
    (write-pattern (scale 1/10 simplex-snoise)       "pattern_7.png")
    (write-pattern (scale 1/10 plasma)               "pattern_8.png")
    (write-pattern (scale 1/10 splasma)              "pattern_9.png"))

(defn predefined-patterns-test
    "Otestování funkcí pro vytváření vzorků/textur 2D obrázků."
    []
    (let [patterns [(checker black white)
                    (scale 1/4 (checker black white))
                    turbulence
                    spots
                    blotches
                    (scale 1/10 perlin-noise)
                    (scale 1/40 perlin-noise)
                    (scale 1/10 perlin-snoise)
                    (scale 1/10 simplex-noise)
                    (scale 1/10 simplex-snoise)
                    (scale 1/10 plasma)
                    (scale 1/10 splasma)]]
        ; postupně projít všemi prvky vektoru "patterns", vytvořit
        ; dvouprvkový vektor [index+patter] a zavolat pro tento
        ; vektor funkci write-pattern
        (doseq [ [i pattern] (map-indexed vector patterns)]
            (write-pattern pattern (str "pattern_" i ".png")))))

(defn composited-patterns-test
    "Otestování funkce compose."
    []
    (let [patterns [(compose turbulence spots)
                    (compose turbulence plasma)
                    (compose (scale 1/40 (checker black white)) plasma)
                    (compose (scale 1/40 (checker black white)) turbulence)]]
        ; postupně projít všemi prvky vektoru "patterns", vytvořit
        ; dvouprvkový vektor [index+patter] a zavolat pro tento
        ; vektor funkci write-pattern
        (doseq [ [i pattern] (map-indexed vector patterns)]
            (write-pattern pattern (str "composite_" i ".png")))))

(defn nested-patterns-test
    []
    (let [patterns [(checker black white)
                    (checker black (scale 1/4 (checker blue yellow)))
                    (checker (scale 1/4 (checker white black)) (scale 1/4 (checker blue yellow)))
                    (checker (rotate 45 (scale 1/4 (checker white black))) (scale 1/4 (checker blue yellow)))
                    (rotate 20 (checker (rotate 20 (scale 1/4 (checker white black)))
                                        (rotate 40 (scale 1/4 (checker blue yellow)))))
                    ]]
        ; postupně projít všemi prvky vektoru "patterns", vytvořit
        ; dvouprvkový vektor [index+patter] a zavolat pro tento
        ; vektor funkci write-pattern
        (doseq [ [i pattern] (map-indexed vector patterns)]
            (write-pattern pattern (str "nested_" i ".png")))))

(defn -main
    [& args]
    (try
        (println "Predefined patterns test...")
        ;(predefined-patterns-test-)
        (predefined-patterns-test)
        (println "Composited patterns test...")
        (composited-patterns-test)
        (println "Nested patterns test...")
        (nested-patterns-test)
        (catch Throwable e
            (println (.toString e)))
        (finally ; jistota, že program vždy korektně skončí
            (System/exit 0))))

