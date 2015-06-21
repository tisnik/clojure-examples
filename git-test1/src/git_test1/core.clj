(ns git-test1.core
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

(defn clone-and-list-branches
    "Funkce naklonuje repositar a vypise vsechny vzdalene vetve."
    [repository-url directory-name]
    (let [repository (clone-repository repository-url directory-name)]
         ; navic jeste zjistime stav repositare
         (println "Repository status: " (jgit/git-status repository))
         (list-branches repository)))

(defn git-test-1
    "Naklonovani repositare a vypis vzdalenych vetvi."
    [repository-url directory-name]
    (try (clone-and-list-branches repository-url directory-name)
         (finally
             (delete-directory directory-name))))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-1 repository-url directory-name))

