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

(ns json-serializer.core
  (:require [jackdaw.admin :as ja]
            [jackdaw.client :as jc]
            [clojure.pprint :as pp]))

(require '[jackdaw.serdes.json])

(defn delete-topic
  [broker-config topic-name]
  (try
    (let [client (ja/->AdminClient broker-config)]
      (ja/delete-topics! client [{:topic-name topic-name}]))
    (catch Exception e (str "caught exception: " (.getMessage e)))))

(defn new-topic
  [broker-config topic-name]
  (try
    (let [client (ja/->AdminClient broker-config)
          topic-config {:topic-name topic-name
                        :partition-count 1
                        :replication-factor 1
                        :key-serde (jackdaw.serdes.json/serde)
                        :value-serde (jackdaw.serdes.json/serde)
                        :topic-config {"cleanup.policy" "compact"}}]
      (ja/create-topics! client [topic-config]))
      (catch Exception e (str "caught exception: " (.getMessage e)))))

(defn produce-messages
  [broker-config topic-name messages]
  ;; konfigurace producenta zprav ve formatu JSON
  (let [producer-config (merge broker-config {"acks" "all"
                                              "client.id" "foo"})
        ; specifikace zpusobu serializace klicu i obsahu zpravy
        producer-serde-config {:key-serde   (jackdaw.serdes.json/serde)
                               :value-serde (jackdaw.serdes.json/serde)}]

    ;; poslani 100 zprav se serializaci klice i hodnoty
    (with-open [producer (jc/producer producer-config producer-serde-config)]
      (doseq [i (range 0 messages)]
        (let [topic {:topic-name topic-name}
              ; posilany klic
              message-key {:n i
                           :foo "foo"}
              ; posilany obsah zpravy
              message-value {:bar "bar"
                             :value i
                             :recip (/ 1 (inc i))
                             :factorial (reduce * (range 1M (inc i)))
                             :values (range i) }
              record-metadata (jc/produce! producer topic message-key message-value)]
          (pp/pprint @record-metadata))))))

(defn -main
  [& args]
  (let [broker-config {"bootstrap.servers" "localhost:9092"}
        topic-name "json"]

    ;; na zacatku pro jistotu vymazeme tema "json"
    (delete-topic broker-config topic-name)

    ;; vytvoreni noveho tematu akceptujiciho zpravy ve formatu JSON
    (new-topic broker-config topic-name)

    ;; poslani 100 zprav se serializaci klice i hodnoty
    (produce-messages broker-config topic-name 100)))
