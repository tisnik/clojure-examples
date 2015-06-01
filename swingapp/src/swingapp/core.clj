(ns swingapp.core
  (:gen-class))

(import 'javax.swing.JFrame)
(import 'javax.swing.JButton)

(defn -main
    [& args]
    (doto (JFrame. "Hello world!")
          (.add (doto (JButton. "Click Me")))
          .pack
          (.setVisible true)))

