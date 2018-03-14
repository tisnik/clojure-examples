(ns iota-test.core-test
  (:require [clojure.test   :refer :all]
            [iota-test.core :refer :all]
            [juxt.iota      :refer [given]]))


(def expected-response
    {:status 200,
     :headers
           {"Content-Type" "text/plain; charset=utf-8",
            "Cache-Control" ["must-revalidate" "no-cache" "no-store"],
            "Expires" "-1",
            "Pragma" "no-cache"},
     :body "hello world!"})


(deftest test-generate-response
    (testing "Function generate-response"
        (given (generate-response "hello world!")
               :status := 200
               :body   :# #"[a-zA-Z !]+"
               [:headers "Pragma"] :? string?
               [:headers "Content-Type"] :? string?
               [:headers "Content-Type"] :? #(.startsWith % "text/plain; ")
               [:headers "Cache-Control"] :> ["no-cache" "no-store"])))
