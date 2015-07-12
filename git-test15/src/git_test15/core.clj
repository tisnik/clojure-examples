(ns git-test15.core
    (:gen-class))

(require '[clojure.pprint     :as pprint])
(require '[clj-jgit.porcelain :as jgit])
(require '[clj-jgit.querying  :as jgit-query])
(require '[hozumi.rm-rf       :as rm-rf])

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

(defn rev-list
    [repo]
    (println "\nList of all revision objects\n")
    (let [rev-list (jgit-query/rev-list repo)]
        (doseq [rev rev-list]
            (let [info (jgit-query/commit-info repo rev)]
                (println "commit" (:id info))
                (println "Author: " (:author info))
                (println "Files: " (:changed_files info))
                (println "\n" (:message info) "\n")
                (println "Branches: " (jgit-query/branches-for repo (:raw info)))
                (println "Changed files: " (jgit-query/changed-files repo (:raw info)))
                (println)
                (println (jgit-query/changed-files-with-patch repo (:raw info)))
                (println)
             ))))

(defn git-test-15
    "Naklonovani repositare, nacteni informaci
     z vytvoreneho adresare a vypis podrobnejsiho logu."
    [repository-url directory-name]
    ; naklonovani repositare do specifikovaneho adresare
    (clone-repository repository-url directory-name)
    ; nacteni informaci o repositari z lokalniho adresare
    ; povsimnete si pouziti "automagicky" vytvorene promenne,
    ; ktera se jmenuje 'repo'
    (try (jgit/with-repo directory-name
                         (rev-list repo))
         (finally
             ; vymazani adresare s naklonovanym repositarem
             (delete-directory directory-name))))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-15 repository-url directory-name))

