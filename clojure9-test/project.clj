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

(defproject clojure9-test "0.1.0-SNAPSHOT"
  :description "Projekt, který zajistí stažení Clojure verze 1.10.1"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :plugins [[lein-codox "0.10.7"]
            ]
  :main ^:skip-aot clojure9-test.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
