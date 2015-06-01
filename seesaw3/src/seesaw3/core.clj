(ns seesaw3.core
    (:gen-class))

(use 'seesaw.core)

(defn -main
    [& args]
    (-> (frame :title "Hello world!"
               :content (button :text "Click me"))
        (pack!)
        (show!)))

