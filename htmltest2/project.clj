;
;  (C) Copyright 2015, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(defproject htmltest1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [hiccup "1.0.4"]]
  :plugins [[lein-codox "0.10.7"]
            [test2junit "1.1.0"]
            ;[lein-test-out "0.3.1"]
            [lein-cloverage "1.0.7-SNAPSHOT"]
            [lein-kibit "0.1.8"]
            [lein-clean-m2 "0.1.2"]
            [lein-project-edn "0.3.0"]
            [lein-marginalia "0.9.1"]]
  :main ^:skip-aot htmltest2.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
