(defproject seesaw2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [seesaw "1.4.5"]]
  :main ^:skip-aot seesaw2.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
