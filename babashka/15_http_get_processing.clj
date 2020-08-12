(let [response (slurp "https://httpbin.org/get")
      parsed   (json/decode response true)
      headers  (:headers parsed)
      user-agent (:User-Agent headers)]
  (println user-agent))
