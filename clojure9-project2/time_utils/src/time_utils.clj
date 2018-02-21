(ns time-utils)

(require '[clj-time.core :as t])
(require '[clj-time.format :as f])

(defn now
    "Returns the current datetime"
    []
    (t/now))

(defn time-str
    "Returns a string representation of a datetime in the local time zone."
    [dt]
    (f/unparse
        (f/with-zone (f/formatter "hh:mm aa") (t/default-time-zone))
        dt))
