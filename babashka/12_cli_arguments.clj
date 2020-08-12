(require '[clojure.pprint :as pprint])
(require '[clojure.tools.cli :refer [parse-opts]])

(def command-line-options
  [["-v" "--verbose" "Verbosity level"
    :id :verbosity
    :default 0
    :update-fn inc]
   ["-p" "--port PORT" "Port number"
    :default 80
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ["-h" "--help"]])

(pprint/pprint (parse-opts *command-line-args* command-line-options))
