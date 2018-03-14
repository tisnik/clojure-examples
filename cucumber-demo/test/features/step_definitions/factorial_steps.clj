(use '[clojure.test])
(use '[cucumber-demo.core])


(def context (atom
    {:input nil
     :result nil}))


(Given #"^The function factorial is callable$"
    []
    (assert (clojure.test/function? 'cucumber-demo.core/factorial)))


(When #"^I try to compute (\d+)!$"
    [input]
    (let [n (bigdec input)]
        (swap! context assoc :input n)
        (swap! context assoc :result (factorial n))))


(Then #"^I should get result (\d+)$"
    [result_str]
    (let [expected (bigdec result_str)
          actual   (:result @context)]
          (assert (= expected actual))))
