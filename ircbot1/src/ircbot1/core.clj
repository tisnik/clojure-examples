;
; Prvni varianta velmi jednoducheho IRC bota
;
; Autor: Pavel Tisnovsky
;

(ns ircbot1.core
  (:gen-class))

(require '[irclj.core :as irc])

(def configuration
    "Datova struktura obsahujici informace o pripojeni na IRC server."
    {:server  "irc.freenode.net" ; vsem dostupny IRC server
     :port    6667               ; vychozi port
     :channel "#botwar"          ; kanal, ktery je urceny pro testovani botu
                                 ; (dostanete zde mj. prava channel operatora)
     :nick    "cljbot42"})       ; tuto cast bude potreba zmenit, nehadejte se
                                 ; prosim o stejny nick :)

(defn create-reply
    "Vytvoreni datove struktury popisujici odpoved."
    [incoming-message]
    ; rozhodnuti, zda se ma odpoved poslat zpet do kanalu nebo na soukromy chat
    (if (.startsWith (:target incoming-message) "#") ; cilem je jmeno kanalu
        incoming-message                             ; -> posleme odpoved zpet na kanal
        ; v opacnem pripade posleme zpravu primo uzivateli
        ; cilem bude prezdivka uzivatele
        (assoc incoming-message :target (:nick incoming-message))))

(defn on-incoming-message
    "Callback funkce volana pri prichodu zpravy na kanal ci na soukromy chat."
    [connection incoming-message]
    ; rozdeleni zpravy na jednotlive prvky
    (let [{text    :text
           target  :target
           nick    :nick
           host    :host
           command :command} incoming-message]
           ; zalogujeme, co se prave stalo
           (println "Received message from" nick "to" target ":" text "(" host command ")")
           ; a vytvorime vhodnou odpoved
           (irc/reply connection (create-reply incoming-message) (str "hello " nick))))

(defn start-irc-bot
    "Funkce, ktera zajisti spusteni IRC bota a jeho pripojeni na zvoleny server a kanal(y)."
    [configuration callback-function]
    (println "Connecting to" (:server configuration) "port" (:port configuration))
    ; vlastni pripojeni na server
    (let [connection (irc/connect (:server configuration)
                                  (:port   configuration)
                                  (:nick   configuration)
                                  :callbacks {:privmsg callback-function})]
        (println "Connected, joining to channel" (:channel configuration))
        ; pripojeni ke zvolenemu kanalu ci kanalum
        (irc/join connection (:channel configuration))
        ; snad je vse ok :)
        (println "Connected...")))

(defn -main
    "Vstupni bod do teto aplikace."
    [& args]
    (start-irc-bot configuration on-incoming-message))

