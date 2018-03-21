(use '[cucumber+expect3.core])
(require '[clojure.test :refer [function?]])
(require '[expectations :refer [expect]])


(def context (atom
    {:input nil
     :result nil}))


(Given #"^The function factorial is callable$"
    []
    (assert (clojure.test/function? 'cucumber+expect3.core/factorial)))


(When #"^I try to compute (-?\d+)!$"
    [input]
    (let [n (bigdec input)]
        (swap! context assoc :input n)
        (swap! context assoc  :result (factorial n))))


(Then #"^I should get result (\d+)$"
    [result_str]
    (let [expected_result (bigdec result_str)
          actual_result   (:result @context)]
          (expect expected_result actual_result)))
