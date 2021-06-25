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

(ns describe-cluster.core
  (:require [jackdaw.admin :as ja]
            [clojure.pprint :as pp]))

(defn -main
  [& args]
  (let [client (ja/->AdminClient {"bootstrap.servers" "localhost:9092"})]
    (pp/pprint (ja/describe-cluster client))))
