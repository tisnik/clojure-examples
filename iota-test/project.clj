(defproject iota-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [juxt/iota "0.2.3"]
                 [ring/ring-core "1.3.2"]]
  :main ^:skip-aot iota-test.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
