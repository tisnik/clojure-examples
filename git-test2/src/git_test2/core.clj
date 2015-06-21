(ns git-test2.core
    (:gen-class))

(require '[clj-jgit.porcelain :as jgit])
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

(defn list-branches
    "Vypis vsech vzdalenych vetvi."
    [repository]
    (println "Remote branch list:")
    (doseq [branch (jgit/git-branch-list repository :remote)]
        (println (.getName branch))))

(defn git-test-2
    "Naklonovani repositare, nacteni informaci
     z vytvoreneho adresare a vypis vzdalenych vetvi."
    [repository-url directory-name]
    ; naklonovani repositare do specifikovaneho adresare
    (clone-repository repository-url directory-name)
    ; nacteni informaci o repositari z lokalniho adresare
    (try (-> (jgit/load-repo directory-name)
             list-branches)
         (finally
             ; vymazani adresare s naklonovanym repositarem
             (delete-directory directory-name))))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-2 repository-url directory-name))

