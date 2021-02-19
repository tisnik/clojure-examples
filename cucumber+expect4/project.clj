;
;  (C) Copyright 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(defproject cucumber+expect4 "0.1.0-SNAPSHOT"
    :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.10.1"]
                   [expectations "2.0.9"]]
    :plugins [[com.siili/lein-cucumber "1.0.7"]
              [lein-expectations "0.0.8"]]
    :cucumber-feature-paths ["test/features/"]
    :main ^:skip-aot cucumber+expect4.core
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}
               :dev {:dependencies [[com.siili/lein-cucumber "1.0.7"]]}})
