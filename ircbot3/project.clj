;
;  (C) Copyright 2021  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(defproject ircbot3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [irclj "0.5.0-alpha4"]]
  :plugins [[lein-codox "0.10.7"]
            [test2junit "1.1.0"]
            ]
  :main ^:skip-aot ircbot3.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
