(ns db-store.core)

(require '[clojure.java.jdbc :as jdbc])

; Struktura reprezentujici zpusob pripojeni do vybrane databaze
(def test-db
    {:classname   "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname     "test.db"
    })

(defn store-user-name
    [db-spec user-name]
    (jdbc/insert! db-spec "users" user-name))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (store-user-name test-db {:id 1 :name "Rich" :surname "Hickey"})
  (store-user-name test-db {:id "foobar" :name "Rich" :surname "Hickey"}))
