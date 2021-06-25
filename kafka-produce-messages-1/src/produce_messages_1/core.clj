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

(ns produce-messages-1.core
  (:require [jackdaw.client :as jc]
            [clojure.pprint :as pp]))

(def producer-config
  {"bootstrap.servers" "localhost:9092"
   "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
   "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
   "acks" "all"
   "client.id" "foo"})

(defn -main
  [& args]
  (with-open [producer (jc/producer producer-config)]
    (let [record-metadata (jc/produce! producer {:topic-name "test1"} "1" "Hello, Kafka!")]
      (pp/pprint @record-metadata))))
