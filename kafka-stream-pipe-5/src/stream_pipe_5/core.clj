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

(ns stream-pipe-5.core
  (:require [jackdaw.admin :as ja]
            [jackdaw.client :as jc]
            [jackdaw.client.log :as jl]
            [jackdaw.serdes.json]
            [jackdaw.streams :as j]
            [clojure.pprint :as pp]
            [clojure.tools.logging :as log]))


(def topic-config
  "Konfigurace témat - vstupního i výstupního."
  {:input
   {:topic-name "input"
    :partition-count 1
    :replication-factor 1
    :key-serde (jackdaw.serdes.json/serde)
    :value-serde (jackdaw.serdes.json/serde)}
   :output-1
   {:topic-name "output1"
    :partition-count 1
    :replication-factor 1
    :key-serde (jackdaw.serdes.json/serde)
    :value-serde (jackdaw.serdes.json/serde)}
   :output-2
   {:topic-name "output2"
    :partition-count 1
    :replication-factor 1
    :key-serde (jackdaw.serdes.json/serde)
    :value-serde (jackdaw.serdes.json/serde)}
   :output-3
   {:topic-name "output3"
    :partition-count 1
    :replication-factor 1
    :key-serde (jackdaw.serdes.json/serde)
    :value-serde (jackdaw.serdes.json/serde)}
   :output-4
   {:topic-name "output4"
    :partition-count 1
    :replication-factor 1
    :key-serde (jackdaw.serdes.json/serde)
    :value-serde (jackdaw.serdes.json/serde)}})


(def app-config
  "Konfigurace aplikace (ve smyslu knihovny Jackdaw)."
  {"application.id" "pipe"
   "bootstrap.servers" "localhost:9092"
   "cache.max.bytes.buffering" "0"
   "default.deserialization.exception.handler" "org.apache.kafka.streams.errors.LogAndContinueExceptionHandler"})


(defn delete-topic
  "Pomocná funkce pro smazání vybraného tématu."
  [broker-config topic]
  (try
    (log/warn "Deleting topic" (:topic-name topic))
    (let [client (ja/->AdminClient broker-config)]
      (ja/delete-topics! client [topic]))
    (catch Exception e (str "caught exception: " (.getMessage e)))))


(defn new-topic
  "Pomocná funkce pro vytvoření nového tématu."
  [broker-config topic]
  (try
    (log/warn "Creating topic" (:topic-name topic))
    (let [client (ja/->AdminClient broker-config)]
      (ja/create-topics! client [topic]))
      (catch Exception e (str "caught exception: " (.getMessage e)))))


(defn etl-1
  "Transformační funkce."
  [[k v]]
  [k {:result (+ (:x v) (:y v))}])


(defn etl-2
  "Transformační funkce."
  [[k v]]
  [k (assoc v :timestamp (str (new java.util.Date)))])


(def etl-3
  "Transformační funkce vytvořená ve formě uzávěru."
  (let [counter (atom 0)]
    (fn [[k v]] (do
                  (swap! counter inc) 
                  [k (assoc v :counter @counter)]))))


(defn build-topology
  "Definice celé pipeliny (kolony) - základ aplikace."
  [builder topic-config]
  (-> (j/kstream builder (:input topic-config))
      (j/peek (fn [[k v]]
                (log/warn "Received message with key:   " k " and value:" v)))
      (j/through (:output-1 topic-config))
      (j/map etl-1)
      (j/peek (fn [[k v]]
                (log/warn "Transformed message with key:" k " and value:" v)))
      (j/through (:output-2 topic-config))
      (j/map etl-2)
      (j/peek (fn [[k v]]
                (log/warn "Transformed message with key:" k " and value:" v)))
      (j/through (:output-3 topic-config))
      (j/map etl-3)
      (j/peek (fn [[k v]]
                (log/warn "Transformed message with key:" k " and value:" v)))
      (j/to (:output-4 topic-config)))
  builder)


(defn start-app
  "Spuštění aplikace."
  [app-config topic-config]
  (let [builder (j/streams-builder)
        topology (build-topology builder topic-config)
        app (j/kafka-streams topology app-config)]
    (log/warn "Starting pipe")
    (j/start app)
    (log/warn "Pipe is up")
    app))


(defn stop-app
  "Zastavení aplikace."
  [app]
  (log/warn "Stopping pipe")
  (j/close app)
  (log/warn "Pipe is down"))


(defn -main
  [& args]
  (let [broker-config {"bootstrap.servers" "localhost:9092"}]

    ;; na začátku pro jistotu vymažeme témata používaná pipou
    (delete-topic broker-config (:input topic-config))
    (delete-topic broker-config (:output-1 topic-config))
    (delete-topic broker-config (:output-2 topic-config))
    (delete-topic broker-config (:output-3 topic-config))

    ;; vytvoření nových témat akceptujících zprávy ve formátu JSON
    (new-topic broker-config (:input topic-config))
    (new-topic broker-config (:output-1 topic-config))
    (new-topic broker-config (:output-2 topic-config))
    (new-topic broker-config (:output-3 topic-config))

    ;; spuštění kolony
    (log/warn "Starting application")
    (let [app (start-app app-config topic-config)]
      (log/warn "App created:" app))))
