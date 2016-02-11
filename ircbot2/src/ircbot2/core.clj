(ns ircbot2.core
  (:gen-class))

(require '[irclj.core :as irc])

(def configuration
    {:server  "irc.freenode.net"
     :port    6667
     :channel "#botwar"
     :nick    "cljbot42"}) ; nehadejte se o stejny nick :)

(defn message-to-channel?
    [message]
    (.startsWith (:target message) "#"))

(defn message-for-me?
    "Vrati logickou 'pravdu' v pripade, ze se byla prijata
     prima zprava ci privatni zprava."
    [my-name message]
    (or (.startsWith (:target message) my-name)        ; privatni zprava
        (.startsWith (:text message) (str my-name ":")); prima zprava
    ))

(defn create-reply
    [incoming-message]
    (if (message-to-channel? incoming-message) ; pokud je cilem primo jmeno kanalu
        incoming-message                       ; nemusime zpravu menit
        (assoc incoming-message :target (:nick incoming-message)))) ; zde ovsem posleme zpravu primo uzivateli

(defn on-incoming-message
    [connection incoming-message]
    (let [{text    :text
           target  :target
           nick    :nick
           host    :host
           command :command} incoming-message]
           (println "Received message from" nick "to" target ":" text "(" host command ")")
           (if (message-for-me? (:nick configuration) incoming-message)
               (irc/reply connection (create-reply incoming-message) (str "hello " nick)))))

(defn start-irc-bot
    [configuration callback-function]
    (println "Connecting to" (:server configuration) "port" (:port configuration))
    (let [connection (irc/connect (:server configuration)
                                  (:port   configuration)
                                  (:nick   configuration)
                                  :callbacks {:privmsg callback-function})]
        (println "Connected, joining to channel" (:channel configuration))
        (irc/join connection (:channel configuration))
        (println "Connected...")))

(defn -main
    [& args]
    (start-irc-bot configuration on-incoming-message))

