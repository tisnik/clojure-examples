(defproject matrix3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [net.mikera/core.matrix "0.34.0"]]
  :main ^:skip-aot matrix3.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
