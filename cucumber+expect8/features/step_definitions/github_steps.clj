(require '[expectations :refer [expect]])


(def URL "https://github.com/")

(def context (atom {:response nil}))

(System/setProperty "https.protocols" "TLSv1,TLSv1.1,TLSv1.2")


(defn request
    [address]
    (let [url        (new java.net.URL address)
          connection (.openConnection url)]
          (.connect connection)
          {:status  (.getResponseCode connection)
           :content (slurp (.getInputStream connection))}))


(Given #"^GitHub is accessible$"
    []
    (let [response (request URL)]
        (expect (:status response) 200)))


(When #"^I access the API endpoint /$"
    []
    (let [response (request (str URL "/"))]
        (swap! context assoc :response response)))


(Then #"^I should receive response with (\d+) status code$"
    [code]
    (let [expected_code (Integer/parseInt code)
          actual_code   (-> @context :response :status)]
          (expect expected_code actual_code)))
