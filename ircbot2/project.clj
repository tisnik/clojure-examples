(defproject ircbot2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [irclj "0.5.0-alpha4"]]
  :main ^:skip-aot ircbot2.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
