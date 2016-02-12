;
; Treti varianta velmi jednoducheho IRC bota:
; bot nyni odpovida pouze na zpravy, ktere jsou mu urceny
; pokud rozpozna cislo, vypocte z neho faktorial
;
; Autor: Pavel Tisnovsky
;

(ns ircbot3.core
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

(defn message-to-channel?
    "Test, zda se jedna o zpravu poslanou na kanal."
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
    "Vytvoreni datove struktury popisujici odpoved."
    [incoming-message]
    ; rozhodnuti, zda se ma odpoved poslat zpet do kanalu nebo na soukromy chat
    (if (message-to-channel? incoming-message) ; pokud je cilem primo jmeno kanalu
        incoming-message                       ; -> posleme odpoved zpet na kanal
        ; v opacnem pripade posleme zpravu primo uzivateli
        ; cilem bude prezdivka uzivatele
        (assoc incoming-message :target (:nick incoming-message))))

(defn text->number
    "Prevod parametru specifikovaneho v param-name na cislo typu BigDecimal."
    [input-text]
    (try
        (let [number (re-find #"\d+" input-text)]
            (if number (bigdec number)))       ; pokus o prevod na BigDecimal
        (catch Exception e nil))) ; pokud se prevod nepovede, vraci se nil

(defn factorial
    "Vypocet faktorialu, varianta pracujici korektne s typem BigDecimal."
    [n]
    (if (neg? n)
        "negative numbers are not supported!"
        (apply *' (range 1 (inc n)))))

(defn prepare-reply-text
    "Priprava odpovedi."
    [input-text]
    (let [number (text->number input-text)]
        ; pokud se podarilo precist cislo
        (if number
            ; vypocte a vrati se jeho faktorial
            (factorial number)
            "?")))

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
           ; test, zda je zprava skutecne urcena pro bota
           (if (message-for-me? (:nick configuration) incoming-message)
               ; vytvorime vhodnou odpoved
               (irc/reply connection (create-reply incoming-message) (prepare-reply-text text)))))

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

