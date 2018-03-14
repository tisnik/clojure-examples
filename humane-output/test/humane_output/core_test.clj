(ns humane-output.core-test
  (:require [clojure.test :refer :all]
            [humane-output.core :refer :all]))


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
        (is (= expected-response
               (generate-response "hello world!")))))

