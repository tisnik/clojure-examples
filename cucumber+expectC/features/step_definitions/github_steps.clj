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

(require '[expectations :refer [expect]])
(require '[clojure.data.json :as json])


(def context (atom {:response nil
                    :api-url nil}))

(System/setProperty "https.protocols" "TLSv1,TLSv1.1,TLSv1.2")


(defn request
    [address]
    (let [url        (new java.net.URL address)
          connection (.openConnection url)]
          (.connect connection)
          {:status  (.getResponseCode connection)
           :content (slurp (.getInputStream connection))}))


(Given #"^REST API for ([A-Za-z]+) service is accessible on URL (.*)$"
    [service url]
    (swap! context assoc :api-url url)
    (let [api-url  (:api-url @context)
          response (request (:api-url @context))]
        (expect (:status response) 200)))


(When #"^I access the API endpoint (.+)$"
    [endpoint]
    (let [api-url  (:api-url @context)
          response (request (str api-url endpoint))]
        (swap! context assoc :response response)))


(When #"^I search for user with nick ([A-Za-z0-9]+)$"
    [nick]
    (let [api-url  (:api-url @context)
          response (request (str api-url "/users/" nick))]
        (swap! context assoc :response response)))


(Then #"^I should receive response with (\d+) status code$"
    [code]
    (let [expected_code (Integer/parseInt code)
          actual_code   (-> @context :response :status)]
          (expect expected_code actual_code)))


(Then #"^I should receive proper JSON response$"
    []
    (let [content (-> @context :response :content)]
        (json/read-str content)))


(Then #"^I should find the user with full name (.+)$"
    [full-name]
    (let [content (-> @context :response :content json/read-str)]
        (expect (get content "name") full-name)))


(Then #"^I should find that the user works for company (.*)$"
    [company-name]
    (let [content (-> @context :response :content json/read-str)]
        (expect (get content "company" "") company-name)))
