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

(ns clisktest4.core
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

(defn construct-filename
    [filename-prefix index]
    (format "%s_%02d.png" filename-prefix index))

(defn noise-patterns-test
    "Otestování funkce vnoise a vsnoise."
    [noise-function filename-prefix]
    (let [patterns [noise-function
                    (monochrome noise-function)
                    (scale 1/10 noise-function)
                    (monochrome (scale 1/10 noise-function))
                    (v- (v* 5 noise-function) 1)
                    (scale 1/4 (offset noise-function (checker yellow blue)))
                    (scale 1/4 (offset (v* 1/5 noise-function) (checker yellow blue)))
                    (scale 1/4 (offset (v* 1/2 noise-function) (checker yellow blue)))
                    (scale 1/4 (offset (v* 2 noise-function)   (checker yellow blue)))
                    (scale 1/4 (offset (v* 5 noise-function)   (checker yellow blue)))
                    (scale 1/4 (rotate (v* 5 noise-function)   (checker yellow blue)))
                    (scale 1/4 (rotate (v* 5 noise-function)   (offset (v* 5 noise-function) (checker yellow blue))))
                    ]]
        ; postupně projít všemi prvky vektoru "patterns", vytvořit
        ; dvouprvkový vektor [index+patter], vytvořit jméno výstupního
        ; souboru a následně zavolat funkci write-pattern
        (doseq [ [i pattern] (map-indexed vector patterns)]
            (write-pattern pattern (construct-filename filename-prefix i)))))

(defn -main
    [& args]
    (try
        (println "Vnoise test...")
        (noise-patterns-test vnoise "vnoise")
        (println "Done")
        (println "Vsnoise test...")
        (noise-patterns-test vsnoise "vsnoise")
        (println "Done")
        (catch Throwable e
            (println (.toString e)))
        (finally ; jistota, že program vždy korektně skončí
            (System/exit 0))))

