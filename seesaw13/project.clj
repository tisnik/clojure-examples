(defproject seesaw13 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [seesaw "1.4.5"]]
  :main ^:skip-aot seesaw13.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
