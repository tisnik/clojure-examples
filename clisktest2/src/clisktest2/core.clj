;
;  (C) Copyright 2015, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns clisktest2.core
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

(defn basic-patterns-test
    "Jednoduché uživatelsky definované vzorky."
    []
    (write-pattern black        "basic0.png")
    (write-pattern 1/2          "basic1.png")
    (write-pattern [2/3 0 2/3]  "basic2.png")
    (write-pattern [x 1/2 y]    "basic3.png")
    (write-pattern [y y x]      "basic4.png")
    (write-pattern pos          "basic5.png"))
 
(def sin-wave1 (v* 0.7 (vsin (v* 11 x))))
(def sin-wave2 (v* 0.5 (vsin (v* 17 y))))
(def sin-wave3 (v* 0.3 (vsin (v* 19 (v+ x y)))))

(defn x2
    "Pomocná funkce při výpočtu vzorků."
    [x]
    (v* (v- x 1/2) (v- x 1/2)))

(defn trickier-patterns-test
    "Složitější uživatelsky definované vzorky."
    []
    (write-pattern (v* (v+ x y) 1/2) "tricky1.png")
    (write-pattern [(v* (v+ x y) 1/2) 0 (vabs (v- x y))] "tricky2.png")
    (write-pattern [x sin-wave1 2/3]                     "tricky3.png")
    (write-pattern [sin-wave1 sin-wave2 sin-wave3]       "tricky4.png")
    (write-pattern (vsin (v* 100 (v+ (x2 x) (x2 y))))    "tricky5.png")
    (write-pattern (rgb-from-hsl (v+ [100 100 100] [x y z]))         "tricky6.png")
    (write-pattern (rgb-from-hsl (v* 3/2 [sin-wave1 sin-wave2 sin-wave3])) "tricky7.png"))

(defn -main
    [& args]
    (try
        (println "Basic patterns test...")
        (basic-patterns-test)
        (println "Trickier patterns test...")
        (trickier-patterns-test)
        (println "Done")
        (catch Throwable e
            (println (.toString e)))
        (finally ; jistota, že program vždy korektně skončí
            (System/exit 0))))

