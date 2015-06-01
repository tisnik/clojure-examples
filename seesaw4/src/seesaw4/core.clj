(ns seesaw4.core
    (:gen-class))

(use 'seesaw.core)

(defn -main
    [& args]
    (let [btn (button :text "Click me"
                      :background "#afa")]
         (listen btn :mouse-clicked (fn [e] (println "Very well"))
                     :mouse-entered (fn [e] (println "Mouse caught"))
                     :mouse-exited  (fn [e] (println "Mouse escaped")))
         (-> (frame :title "Hello world!"
                    :on-close :exit
                    :content btn)
             (pack!)
             (show!))))

