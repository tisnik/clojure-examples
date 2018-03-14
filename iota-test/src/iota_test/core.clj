(ns iota-test.core
    (:gen-class))

(require '[ring.util.response :as response])

(defn cache-control-headers
    "Update the response to contains all cache-control headers."
    [response]
    (-> response
        (assoc-in [:headers "Cache-Control"] ["must-revalidate" "no-cache" "no-store"])
        (assoc-in [:headers "Expires"] "0")
        (assoc-in [:headers "Pragma"] "no-cache")))

(defn generate-response
    [content]
    (-> (response/response content)
        (response/content-type "text/plain; charset=utf-8")
        cache-control-headers))

(defn -main
    [& args]
    (println (generate-response "Hello world!")))
