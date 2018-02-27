(defproject db-store "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.9.0"]
                   [org.clojure/java.jdbc "0.3.5"]
                   [org.xerial/sqlite-jdbc "3.7.2"]]
    :main ^:skip-aot db-store.core
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}})
