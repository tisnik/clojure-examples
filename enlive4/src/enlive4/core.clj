(ns enlive4.core
    (:gen-class))

(require '[net.cgrand.enlive-html :as html])

(def roles [
    {:actor-name "Zdeněk Svěrák"   :character "inspektor Trachta  "}
    {:actor-name "Petr Brukner"    :character "praktikant Hlaváček"}
    {:actor-name "Miloň Čepelka"   :character "praktikant Hlaváček"}
    {:actor-name "Bořivoj Penc"    :character "továrník Bierhanzel"}
    {:actor-name "Jaroslav Weigel" :character "továrník Bierhanzel"}
    {:actor-name "Jan Hraběta"     :character "továrník Meyer"}
    {:actor-name "Václav Kotek"    :character "steward"}
    {:actor-name "Genadij Rumlena" :character "steward"}])

(def vrazda-v-salonnim-coupe
    {:title "Vražda v salonním coupé"
     :roles roles
    })

(html/defsnippet one-record "test.html"
    {[:#role]     ; zacatek
     [:#role]}    ; konec
    [record]
    [:#actor]      (html/content (:actor-name record))
    [:#character]  (html/content (:character record)))

(html/deftemplate test-page "test.html"
    [data-for-page]
    [:title]       (html/content (:title data-for-page))
    [:h1 :span]    (html/content (:title data-for-page))
    [:#roles]      (html/content (map one-record (:roles data-for-page))) ; vnitrek odstavce bude duplikovan
)

(defn -main
    [& args]
    (println (reduce str (test-page vrazda-v-salonnim-coupe))))

