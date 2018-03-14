(defproject cucumber-demo "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.9.0"]]
    :plugins [[com.siili/lein-cucumber "1.0.7"]]
    :cucumber-feature-paths ["test/features/"]
    :main ^:skip-aot cucumber-demo.core
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}
               :dev {:dependencies [[com.siili/lein-cucumber "1.0.7"]]}})
