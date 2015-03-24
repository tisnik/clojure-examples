(ns sqltest3.core
  (:gen-class))

(require '[clojure.pprint    :as pprint])
(require '[clojure.java.jdbc :as jdbc])

; Struktura reprezentujici zpusob pripojeni do vybrane databaze
(def test-db
    {:classname   "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname     "test.db"
    })

(defn read-whole-table
    "Nacteni obsahu vybrane tabulky."
    [db-spec table-name]
    (jdbc/query db-spec (str "select * from " table-name " order by postava, jmeno")))

(defn list-table
    "Vypis obsahu vybrane tabulky."
    [db-spec table-name]
    (try
        (let [results (read-whole-table db-spec table-name)]
            (doseq [result results]
                (println (format "%-10s %-20s" (:postava result) (:jmeno result)))))
        (catch Exception e
            (println "error accessing database '" (:subname db-spec) "'!"))))

(defn -main
    "Vstupni bod do aplikace."
    [& args]
    (list-table test-db "obsazeni"))

