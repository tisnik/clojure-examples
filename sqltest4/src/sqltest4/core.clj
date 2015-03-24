(ns sqltest4.core
  (:gen-class))

(require '[clojure.pprint    :as pprint])
(require '[clojure.java.jdbc :as jdbc])

; Struktura reprezentujici zpusob pripojeni do vybrane databaze
(def test-db
    {:classname   "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname     "test.db"
    })

(def table-name "obsazeni")

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

(defn fill-in-table
    "Zapis dat do tabulky."
    [db-spec table-name]
     (jdbc/insert! db-spec table-name {:postava "inspektor"  :jmeno "Trachta"})
     (jdbc/insert! db-spec table-name {:postava "praktikant" :jmeno "Hlavacek"})
     (jdbc/insert! db-spec table-name {:postava "tovarnik"   :jmeno "Bierhanzel"})
     (jdbc/insert! db-spec table-name {:postava "tovarnik"   :jmeno "Meyer"})
     (jdbc/insert! db-spec table-name {:postava "stevard"    :jmeno "Vaclav Kotek"}))

(defn -main
    "Vstupni bod do aplikace."
    [& args]
    (fill-in-table test-db table-name)
    (list-table test-db table-name))

