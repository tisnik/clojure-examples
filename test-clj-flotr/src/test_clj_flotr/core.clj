(ns test-clj-flotr.core
  (:gen-class))

(require '[hiccup.page         :as page])
(require '[clj-flotr.generator :as generator])

(defn test-pie-charts
    "Creates HTML page with various types of pie-charts."
    []
    (let [data [{:values 10 :label "foo"}
                {:values 10 :label "bar"}
                {:values 30 :label "baz"}]]
        (spit "test-pie-charts.html" (page/xhtml
            [:head
                [:title "Test Pie Charts"]
                [:meta {:name "Generator" :content "Clojure"}]
                [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
                (generator/flotr-scripts nil nil nil)
            ]
            [:body
                [:h1 "Test Pie Charts"]
                [:table
                    [:tr
                        [:td (generator/pie-chart "chart1" "300px" "300px" data)]
                        [:td (generator/pie-chart "chart2" "300px" "300px" data
                                :vertical-lines true)]
                        [:td (generator/pie-chart "chart3" "300px" "300px" data
                                :horizontal-lines true)]
                        [:td (generator/pie-chart "chart4" "300px" "300px" data
                                :horizontal-lines true
                                :vertical-lines true)]
                    ] ; </tr>
                    [:tr
                        [:td (generator/pie-chart "chart5" "300px" "300px" data
                                 :show-legend true)]
                        [:td (generator/pie-chart "chart6" "300px" "300px" data
                                 :show-legend true
                                 :legend-position "sw")]
                        [:td (generator/pie-chart "chart7" "300px" "300px" data
                                 :show-legend true
                                 :legend-position "ne"
                                 :legend-background "#D2E8FF")]
                        [:td (generator/pie-chart "chart8" "300px" "300px" data
                                 :show-legend true
                                 :legend-position "sw"
                                 :legend-background "#FFFFFF")]
                    ] ; </tr>
                ] ; </table>
                [:h1 "done"]]))))

(defn test-stacked-bars
    "Creates HTML page with various types of stacked-bars chart."
    []
    (let [data [{:values [[0 1] [1 2] [2 3] [3 3]] :label "foo"}
                {:values [[0 1] [1 4] [2 6] [3 6]] :label "bar"}
                {:values [[0 0] [1 1] [2 0] [3 0]] :label "baz"}]]
        (spit "test-stacked-bars.html" (page/xhtml
            [:head
                [:title "Test Stacked Bars"]
                [:meta {:name "Generator" :content "Clojure"}]
                [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
                (generator/flotr-scripts nil nil nil)
            ]
            [:body
                [:h1 "Test Stacked Bars"]
                [:table
                    [:tr
                        [:td (generator/stacked-bars "chart1" "300px" "300px" data)]
                        [:td (generator/stacked-bars "chart2" "300px" "300px" data
                                :vertical-lines true)]
                        [:td (generator/stacked-bars "chart3" "300px" "300px" data
                                :horizontal-lines true)]
                        [:td (generator/stacked-bars "chart4" "300px" "300px" data
                                :horizontal-lines true
                                :vertical-lines true)]
                    ] ; </tr>
                    [:tr
                        [:td (generator/stacked-bars "chart5" "300px" "300px" data
                                 :show-legend true)]
                        [:td (generator/stacked-bars "chart6" "300px" "300px" data
                                 :show-legend true
                                 :legend-position "sw")]
                        [:td (generator/stacked-bars "chart7" "300px" "300px" data
                                 :show-legend true
                                 :legend-position "ne"
                                 :legend-background "#D2E8FF"
                                 :bar-width "0.1")]
                        [:td (generator/stacked-bars "chart8" "300px" "300px" data
                                 :show-legend true
                                 :legend-position "sw"
                                 :legend-background "#FFFFFF"
                                 :bar-width "1")]
                    ] ; </tr>
                ] ; </table>
                [:h1 "done"]]))))

(defn -main
    "Hey, I wanna some pretty charts!"
    [& args]
    (test-pie-charts)
    (test-stacked-bars))

