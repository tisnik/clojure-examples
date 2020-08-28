;
;  (C) Copyright 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(use '[cucumber+expect4.core])
(require '[clojure.test :refer [function?]])
(require '[expectations :refer [expect]])


(def context (atom
    {:input nil
     :result nil
     :exception nil}))


(Given #"^The function factorial is callable$"
    []
    (assert (clojure.test/function? 'cucumber+expect4.core/factorial)))


(When #"^I try to compute (-?\d+)!$"
    [input]
    (let [n (bigdec input)]
        (swap! context assoc :input n)
        (try
            (swap! context assoc  :result (factorial n))
            (swap! context dissoc :exception)
            (catch Exception e
                (swap! context dissoc :result)
                (swap! context assoc  :exception e)))))


(Then #"^I should get result (\d+)$"
    [result_str]
    (let [expected_result (bigdec result_str)
          actual_result   (:result @context)
          exception       (:exception @context)]
          (expect nil? exception)
          (expect expected_result actual_result)))


(defn exception-name
     [exception]
     (-> exception .getClass .getSimpleName))


(Then #"^the ([A-Za-z]+) should be thrown$"
    [exception_name]
    (let [exception (:exception @context)
          result    (:result @context)]
         (expect nil? result)
         (expect exception)
         (expect exception_name (exception-name exception))))


(Then #"^the ([A-Za-z]+) should be thrown with message (.+)$"
    [exception_name message]
    (let [exception (:exception @context)
          result    (:result @context)]
         (expect nil? result)
         (expect exception)
         (expect exception_name (exception-name exception))))
