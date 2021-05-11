(ns rhizome-1.core)

(require '[rhizome.viz :as viz])


(defn -main
  [& args]
  (let [graph {:a [:b :c]
               :b [:c]
               :c [:a]}]
    (viz/view-graph (keys graph) graph
                    :node->descriptor (fn [n] {:label n}))))
