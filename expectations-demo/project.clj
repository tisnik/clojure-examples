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

(defproject expectations-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [expectations "2.0.9"]]
  :plugins [[lein-codox "0.10.7"]
            [test2junit "1.1.0"]
            ;[lein-test-out "0.3.1"]
            [lein-cloverage "1.0.7-SNAPSHOT"]
            ]
  :main ^:skip-aot expectations-demo.core
  :target-path "target/%s"
  :plugins [[lein-expectations "0.0.8"]]
  :profiles {:uberjar {:aot :all}})
