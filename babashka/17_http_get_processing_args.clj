#!/usr/bin/env bb

(require '[clojure.pprint :as pprint])
(require '[clojure.tools.cli :refer [parse-opts]])

(import (java.net InetAddress))

(def command-line-options
  [["-H" "--hostname HOST" "Remote host"
    :default "localhost"
    :required true
    ]])

(let [opts (parse-opts *command-line-args* command-line-options)
      url  (-> opts :options :hostname)]
  (-> (str "https://" url "/get")
      slurp
      (json/decode true)
      pprint/pprint))
