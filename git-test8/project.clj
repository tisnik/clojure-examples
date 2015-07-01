(defproject git-test8 "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.6.0"]
                   [clj-jgit "0.8.0"]
                   [clj-rm-rf "1.0.0-SNAPSHOT"]]
    :main ^:skip-aot git-test8.core
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}})
