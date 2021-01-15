;
;  (C) Copyright 2016, 2020, 2021  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(defproject async8 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License",
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.async "0.2.374"]]
  :plugins [[lein-codox "0.10.7"]
            [lein-project-edn "0.3.0"]
            [lein-marginalia "0.9.1"]]
  :project-edn {:output-file "doc/details.clj"}
  :main ^:skip-aot async8.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
