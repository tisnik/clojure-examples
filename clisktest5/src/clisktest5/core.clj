(ns clisktest5.core
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

(defn mandelbrot-fractal
    "Výpočet Mandelbrotovy množiny."
    [center-x center-y scale maxiter]
    (let [xmin (- center-x (/ 1.0 scale))
          xmax (+ center-x (/ 1.0 scale))
          ymin (- center-y (/ 1.0 scale))
          ymax (+ center-y (/ 1.0 scale))]
        (viewport [xmin ymin] [xmax ymax]
            (fractal
                ; podmínka pro ukončení iterační smyčky
                :while (v- 2 (length [x y]))
                ; výpočet z=z^2+c převedený na operace nad vektory
                :update (v+ c [(v- (v* x x) (v* y y)) ; zx^2-zy^2 + cx
                               (v* 2 x y)])           ; 2*zx*zy + cy
                ; výpočet barvy výsledného vzorku
                :result (v* 'i 0.01)
                ; barva, která se vrátí ve chvíli,
                ; kdy se dosáhne maximálního počtu iterací
                :bailout-result black
                ; maximální počet iterací
                :max-iterations maxiter))))

(defn cubic-mandelbrot-fractal
    "Výpočet kubické Mandelbrotovy množiny."
    [center-x center-y scale maxiter]
    (let [xmin (- center-x (/ 1.0 scale))
          xmax (+ center-x (/ 1.0 scale))
          ymin (- center-y (/ 1.0 scale))
          ymax (+ center-y (/ 1.0 scale))]
        (viewport [xmin ymin] [xmax ymax]
            (fractal
                ; podmínka pro ukončení iterační smyčky
                :while (v- 2 (length [x y]))
                ; výpočet z=z^2+c převedený na operace nad vektory
                :update (v+ c [(v- (v* x x x)   (v* 3 x y y)) ; zxn= zx*zx*zx-3.0*zx*zy*zy+cx;
                               (v- (v* 3 x x y) (v* y y y))]) ; zyn=-zy*zy*zy+3.0*zx*zx*zy+cy;
                ; výpočet barvy výsledného vzorku
                :result (v* 'i 0.01)
                ; barva, která se vrátí ve chvíli,
                ; kdy se dosáhne maximálního počtu iterací
                :bailout-result black
                ; maximální počet iterací
                :max-iterations maxiter))))


(defn mandelbrot-rendering-test
    []
    ;                      x0   y0 scale maxiter filename
    (let [mandel-params [[-1/2  0  3/4   1000    "mandelbrot1.png"]
                         [ 0   -1  4     1000    "mandelbrot2.png"]
                         [ 0   -1  10    1000    "mandelbrot3.png"]
                         [-1.74809088500000000 0.00069335009900000 110116 1000 "mandelbrot4.png"]
                         [-0.80594802749999990 0.20140617800000000 50 10000 "mandelbrot5.png"]]]
        (doseq [mandel-param mandel-params]
            (-> (mandelbrot-fractal (nth mandel-param 0)
                                    (nth mandel-param 1)
                                    (nth mandel-param 2)
                                    (nth mandel-param 3))
                (write-pattern (nth mandel-param 4))))))

(defn fractal-rendering-test
    []
    (mandelbrot-rendering-test)
    (-> (cubic-mandelbrot-fractal 0 0 3/4 1000) (write-pattern "mandelbrot6.png")))

(defn -main
    [& args]
    (try
        (println "fractal rendering test...")
        (fractal-rendering-test)
        (println "Done")
        (catch Throwable e
            (println (.toString e)))
        (finally ; jistota, že program vždy korektně skončí
            (System/exit 0))))

