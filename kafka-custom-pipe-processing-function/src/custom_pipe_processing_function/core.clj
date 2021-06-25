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

(ns custom-pipe-processing-function.core
  (:require [jackdaw.admin      :as ja]
            [jackdaw.client     :as jc]
            [jackdaw.client.log :as jl]
            [clojure.pprint     :as pp]))

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


;; konfigurace serializatoru a deserializatoru
(def consumer-serde-config
  {:key-serde   (jackdaw.serdes.json/serde)
   :value-serde (jackdaw.serdes.json/serde)})


(defn pipe
  [broker-config input-topic output-topic etl-function]
  ;; konfigurace producenta i konzumenta zprav ve formatu JSON
  (let [producer-config (merge broker-config {"acks" "all"
                                              "client.id" "foo"})
        consumer-config (merge broker-config {"key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
                                              "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
                                              "auto.offset.reset" "earliest"
                                              "group.id"  "group-A"})
        ; specifikace zpusobu serializace klicu i obsahu zpravy
        producer-serde-config {:key-serde   (jackdaw.serdes.json/serde)
                               :value-serde (jackdaw.serdes.json/serde)}
        consumer-serde-config {:key-serde   (jackdaw.serdes.json/serde)
                               :value-serde (jackdaw.serdes.json/serde)}]

    ; otevreni konzumenta zprav
    (with-open [consumer (-> (jc/consumer consumer-config consumer-serde-config)
                             (jc/subscribe [{:topic-name input-topic}]))]
      ; otevreni producenta zprav
      (with-open [producer (jc/producer producer-config producer-serde-config)]
        ; cteni zprav ze vstupniho tematu
        (doseq [{:keys [key value partition timestamp offset]} (jl/log consumer 10)]
          (println "Incomming message:")
          (println "key: " key)
          (println "value: " value)
          (println "partition: " partition)
          (println "timestamp: " timestamp)
          (println "offset: " offset)
          (println)
          ; vypocet vysledku s jeho poslanim do vystupniho tematu
          (let [message (etl-function value)
                record-metadata (jc/produce! producer {:topic-name output-topic} (:key message) (:value message))]
            (println "Result:")
            (println "message" message)
            (println)
            (println "Outgoing message:")
            (pp/pprint @record-metadata)
            (println "------------------")))))))


(defn etl
  [input-value]
  {:key {:id (.toString (java.util.UUID/randomUUID))}
   :value (* (:x input-value) (:y input-value))})


(defn -main
  [& args]
  (let [broker-config {"bootstrap.servers" "localhost:9092"}
        input-topic-name "input"
        output-topic-name "output"]

    ;; na zacatku pro jistotu vymazeme temata pouzivane pipou
    (delete-topic broker-config input-topic-name)
    (delete-topic broker-config output-topic-name)

    ;; vytvoreni novych tamat akceptujiciho zpravy ve formatu JSON
    (new-topic broker-config input-topic-name)
    (new-topic broker-config output-topic-name)

    ;; spusteni kolony
    (println "Starting pipe")
    (pipe broker-config input-topic-name output-topic-name etl)))
