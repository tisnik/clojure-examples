(ns seesaw2.core
    (:gen-class))

(use 'seesaw.core)

(defn -main
    [& args]
    (let [main-frame (frame :title "Hello world!")
          btn        (button :text "Click Me")]
          (-> main-frame
              (config! :content btn)
              (pack!)
              (show!))))

