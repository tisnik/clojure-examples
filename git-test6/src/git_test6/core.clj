(ns git-test6.core
    (:gen-class))

(require '[clj-jgit.porcelain :as jgit])
(require '[hozumi.rm-rf       :as rm-rf])

(def repository-url
    "Adresa GIT repositare vcetne specifikace protokolu."
    "https://github.com/tisnik/testrepo.git")

(def directory-name
    "Jmeno adresare, do ktereho se GIT repositar naklonuje."
    "repo")

(defn delete-directory
    "Smazani adresare vcetne podadresaru a souboru."
    [directory]
    (rm-rf/rm-r (java.io.File. directory)))

(defn clone-repository
    "Naklonovani GIT repositare do specifikovaneho adresare."
    [url directory]
    (jgit/git-clone url directory))

(defn git-test-6
    "Naklonovani repositare a pridani noveho souboru."
    [repository-url directory-name]
    ; naklonovani repositare do specifikovaneho adresare
    (clone-repository repository-url directory-name)
    ; budeme pracova s repositarem, ktery je v nasledujicim bloku
    ; predstavovan symbolem 'repo'
    (jgit/with-repo directory-name
         ; stav repa na zacatku
         (println "Repository status: " (jgit/git-status repo))

         ; vytvoreni noveho souboru
         (spit (str directory-name "/answer.txt") "*42*")
         ; modifikace stavajiciho souboru
         (spit (str directory-name "/README.md") "new text")
         ; novy stav repa
         (println "Repository status: " (jgit/git-status repo))

         ; pridani zmen do stage area
         (jgit/git-add repo "answer.txt")
         (jgit/git-add repo "README.md")
         ; novy stav repa
         (println "Repository status: " (jgit/git-status repo))

         ; provedeme commit vsech zmen
         (jgit/git-commit repo "The Answer to the Ultimate Question of Life, the Universe, and Everything")
         ; konecny stav repa
         (println "Repository status: " (jgit/git-status repo))
    )
    (delete-directory directory-name))

(defn -main
    "Funkce zavolana po zadani prikazu 'lein run'."
    [& args]
    (git-test-6 repository-url directory-name))

