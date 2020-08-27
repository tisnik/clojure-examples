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

(ns spec-demo11.core)

(require '[clojure.pprint :as pprint])
(require '[clojure.spec.alpha :as spec])

(spec/def ::byte         (spec/and nat-int? #(<= % 255)))
(spec/def ::ip-address   (spec/tuple ::byte ::byte ::byte ::byte))
(spec/def ::ip-address-2 (spec/cat :1st ::byte :2nd ::byte :3rd ::byte :4th ::byte))


(defn explain-validation->data
    [validation-key value]
    (println "validation-key" validation-key)
    (println "value" value)
    (-> (spec/explain-data validation-key value) (pprint/pprint))
    (println))


(defn -main
    [& args]

    (let [ip-addresses [[127 0 0 1]
                        [255 255 255 255]
                        [192 168 0 1]
                        [0 0 0 -1]
                        [1 2 3 4 5]
                        ["x" "y" "z" "w"]]]
         (doseq [ip-address ip-addresses]
             (explain-validation->data ::ip-address ip-address)))

    (let [ip-addresses [[127 0 0 1]
                        [255 255 255 255]
                        [192 168 0 1]
                        [0 0 0 -1]
                        [1 2 3 4 5]
                        ["x" "y" "z" "w"]]]
         (doseq [ip-address ip-addresses]
             (explain-validation->data ::ip-address-2 ip-address))))

