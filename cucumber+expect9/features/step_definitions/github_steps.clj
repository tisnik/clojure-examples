(require '[expectations :refer [expect]])
(require '[clojure.data.json :as json])


(def URL "https://github.com/")
(def API-URL "https://api.github.com")

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


(When #"^I access the API endpoint (.+)$"
    [endpoint]
    (let [response (request (str API-URL endpoint))]
        (swap! context assoc :response response)))


(When #"^I search for user with nick ([A-Za-z0-9]+)$"
    [nick]
    (let [response (request (str API-URL "/users/" nick))]
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


(Then #"^I should find that the user works for company (.+)$"
    [company-name]
    (let [content (-> @context :response :content json/read-str)]
        (expect (get content "company") company-name)))
