(-> "https://httpbin.org/get"
    slurp
    (json/decode true)
    :headers
    :User-Agent
    println)
