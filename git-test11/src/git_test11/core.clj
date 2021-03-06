(ns git-test11.core
    (:gen-class))

(require '[clj-jgit.porcelain :as jgit])
(require '[clj-jgit.querying  :as jgit-query])
(require '[hozumi.rm-rf :as rm-rf])

(def repository-url
    "Adresa GIT repositare vcetne specifikace protokolu."
    "https://github.com/tisnik/testrepo.git")

(def directory-name
    "Jmeno adresare, do ktereho se GIT repositar naklonuje."
    "repo")

(defn clone-repository
    "Naklonovani GIT repositare do specifikovaneho adresare."
    [url directory]
    (jgit/git-clone url directory))

(defn delete-directory
    "Smazani adresare vcetne podadresaru a souboru."
    [directory]
    (rm-rf/rm-r (java.io.File. directory)))

(defn get-config-option
    [repo x y z]
    (-> repo
        (.getRepository)
        (.getConfig)
        (.getString x y z)))

(defn print-repo-configuration
    [repo]
    (println "GIT repo origin URL:  " (get-config-option repo "remote" "origin" "url"))
    (println "Remote origin fetch:  " (get-config-option repo "remote" "origin" "fetch"))
    (println "Branch master remote: " (get-config-option repo "branch" "master" "remote")))

(defn git-test-11
    "Naklonovani repositare, nacteni informaci
     z vytvoreneho adresare a vypis podrobnejsiho logu."
    [repository-url directory-name]
    ; naklonovani repositare do specifikovaneho adresare
    (clone-repository repository-url directory-name)
    ; nacteni informaci o repositari z lokalniho adresare
    ; povsimnete si pouziti "automagicky" vytvorene promenne,
    ; ktera se jmenuje 'repo'
    (try (jgit/with-repo directory-name
                         (print-repo-configuration repo))
         (finally
             ; vymazani adresare s naklonovanym repositarem
             (delete-directory directory-name))))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-11 repository-url directory-name))

