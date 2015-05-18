(ns clisktest6.core
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

(defn predefined-textures-test
    []
    (let [textures [agate
                    clouds
                    velvet
                    flecks
                    wood
                    ]]
        ; postupně projít všemi prvky vektoru "textures", vytvořit
        ; dvouprvkový vektor [index+patter], vytvořit jméno výstupního
        ; souboru a následně zavolat funkci write-texture
        (doseq [ [i texture] (map-indexed vector textures)]
            (write-pattern texture (str "texture_" i ".png")))))

(defn -main
    [& args]
    (try
        (println "Predefined textures test...")
        (predefined-textures-test)
        (println "Done")
        (catch Throwable e
            (println (.toString e)))
        (finally ; jistota, že program vždy korektně skončí
            (System/exit 0))))

