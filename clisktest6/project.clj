;
;  (C) Copyright 2015, 2020, 2021  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(defproject clisktest6 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [net.mikera/clisk "0.10.0"]]
  :plugins [[lein-codox "0.10.7"]
            [test2junit "1.1.0"]
            ]
  :main ^:skip-aot clisktest6.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
