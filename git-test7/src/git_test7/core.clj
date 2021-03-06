(ns git-test7.core
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

(defn print-customized-log
    "Vypis logovacich informaci ve zvolenem formatu."
    [repository]
    (println "\nLog:")
    (doseq [log-entry (jgit/git-log repository)]
        (let [info (jgit-query/commit-info repository log-entry)]
             (println (:author info) ":" (:message info)))))

(defn print-local-branches
    "Vypis vsech lokalnich vetvi pro vybrany repositar."
    [repo]
    (println "\nLocal branches")
    (doseq [branch (jgit/git-branch-list repo)]
        (println (.getName branch))))

(defn git-test-7
    "Naklonovani repositare, nacteni informaci
     z vytvoreneho adresare a vypis podrobnejsiho logu."
    [repository-url directory-name]
    ; naklonovani repositare do specifikovaneho adresare
    (clone-repository repository-url directory-name)
    ; nacteni informaci o repositari z lokalniho adresare
    ; povsimnete si pouziti "automagicky" vytvorene promenne,
    ; ktera se jmenuje 'repo'
    (try (jgit/with-repo directory-name
                         (print-customized-log repo)
                         (print-local-branches repo))
         (finally
             ; vymazani adresare s naklonovanym repositarem
             (delete-directory directory-name))))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-7 repository-url directory-name))

