(defproject clisktest6 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [net.mikera/clisk "0.10.0"]]
  :main ^:skip-aot clisktest6.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
