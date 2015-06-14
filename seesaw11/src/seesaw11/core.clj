(ns seesaw11.core
    (:gen-class))

(use 'seesaw.core)
(use 'seesaw.color)

(defn input1
    [e]
    (println (input "Type anything")))

(defn input2
    [e]
    (println (input "Type anything" :type :warning)))

(defn input3
    [e]
    (println (input "Type anything" :type :error)))

(defn input4
    [e]
    (println (input "Type anything" :type :info)))

(defn input5
    [e]
    (println (input "Type anything" :type :question)))

(defn input6
    [e]
    (println (input "Type anything" :value 42)))

(defn input7
    [e]
    (println (input "Pick a color"
              :choices ["red" "green" "blue" "yellow" "orange" "pink"])))

(defn input8
    [e]
    (println (input "Pick a color"
              :type :question
              :choices ["red" "green" "blue" "yellow" "orange" "pink"])))

(def formular
    (grid-panel :columns 2
                :rows 4
                :items [(button :text "Plain input"
                                :background :red
                                :listen [:action input1])
                        (button :text "Warning"
                                :background :yellow
                                :listen [:action input2])
                        (button :text "Error"
                                :background :orange
                                :listen [:action input3])
                        (button :text "Info"
                                :background "#ff8080"
                                :listen [:action input4])
                        (button :text "Question"
                                :background "#8080ff"
                                :listen [:action input5])
                        (button :text "Init value"
                                :background "#8f8"
                                :listen [:action input6])
                        (button :text "Choice"
                                :background "#ff8"
                                :listen [:action input7])
                        (button :text "Choice + question"
                                :background "#f8f"
                                :listen [:action input8])
                        ]))

(defn -main
    [& args]
    (-> (frame :title "Color test"
               :on-close :exit
               :content formular)
        (pack!)
        (show!)))

