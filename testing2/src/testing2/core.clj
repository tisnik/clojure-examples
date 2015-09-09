(ns testing2.core
    (:gen-class))

(require '[clojure.java.jdbc :as jdbc])

(def changes-db
    {:classname   "org.sqlite.JDBC"
     :subprotocol "sqlite"
     :subname     "changes.db"
    })

(defn read-changes-for-user
    [user-name]
    (jdbc/query changes-db
        [(str "select * from changes where user_name=? order by id;") user-name]))

(defn store-changes
    [user-name package description date]
    (jdbc/insert! changes-db :changes
        {:date_time date :user_name user-name :package package :description description}))

(defn count-changes-for-user
    [user-name]
    (count (read-changes-for-user user-name)))

(defn -main
    [& args]
    (println "Hello, World!"))

