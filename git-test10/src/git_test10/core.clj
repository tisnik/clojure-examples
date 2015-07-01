(ns git-test10.core
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

(defn print-remote-branches
    "Vypis vsech vzdalenych vetvi pro vybrany repositar."
    [repo]
    (println "\nRemote branches")
    (doseq [branch (jgit/git-branch-list repo :remote)]
        (println (.getName branch))))

(defn print-branches
    [repo]
    (print-local-branches repo)
    (print-remote-branches repo))

(defn checkout
    [repo branch-name]
    (try
        (jgit/git-checkout repo branch-name)
        (catch Exception e
            (println "Unable to checkout: " e))))

(defn git-test-10
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
                         (print-branches repo)

                         (println "\nCheckout to branch-1")
                         (checkout repo "refs/remotes/origin/branch-1") 
                         (print-branches repo)

                         (println "\nCreating new branch")
                         (jgit/git-branch-create repo "new-branch")
                         (print-branches repo)

                         (println "\nCheckout to new branch")
                         (checkout repo "new-branch") 
                         (print-branches repo))
         (finally
             ; vymazani adresare s naklonovanym repositarem
             (delete-directory directory-name))))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-10 repository-url directory-name))

