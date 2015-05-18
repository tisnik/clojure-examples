(ns clisktest7.core
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

(defn voronoi-map-test
    []
    (let [voronoi1 (voronoi :points 10)
          voronoi2 (voronoi :points 100)
          voronoi3 (voronoi :points 1000)]
         (write-pattern (voronoi-blocks :voronoi voronoi1) "voronoi1.png")
         (write-pattern (voronoi-blocks :voronoi voronoi2) "voronoi2.png")
         (write-pattern (voronoi-blocks :voronoi voronoi3) "voronoi3.png")
         (write-pattern (v* 2.0 (voronoi-blocks :voronoi voronoi1)) "voronoi4.png")
         (write-pattern (v* 2.0 (voronoi-blocks :voronoi voronoi2)) "voronoi5.png")
         (write-pattern (v* 2.0 (voronoi-blocks :voronoi voronoi3)) "voronoi6.png")
         (write-pattern (v* (v* 20.0 (voronoi-blocks :voronoi voronoi2))
                        (warp (voronoi-points :voronoi voronoi1) grain)) "voronoi7.png")
         (write-pattern (v* (v* 20.0 (voronoi-blocks :voronoi voronoi2))
                        (warp (voronoi-points :voronoi voronoi2) grain)) "voronoi8.png")
         (write-pattern (v* (v* 20.0 (voronoi-blocks :voronoi voronoi3))
                        (warp (voronoi-points :voronoi voronoi3) grain)) "voronoi9.png")))

(defn -main
    [& args]
    (try
        (println "Voronoi map test...")
        (voronoi-map-test)
        (println "Done")
        (catch Throwable e
            (println (.toString e)))
        (finally ; jistota, že program vždy korektně skončí
            (System/exit 0))))

