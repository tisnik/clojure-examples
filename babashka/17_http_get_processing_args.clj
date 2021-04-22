;
;  (C) Copyright 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

#!/usr/bin/env bb

(require '[clojure.pprint :as pprint])
(require '[clojure.tools.cli :refer [parse-opts]])

(import (java.net InetAddress))

(def command-line-options
  [["-H" "--hostname HOST" "Remote host"
    :default "localhost"
    :required true
    ]])

(let [opts (parse-opts *command-line-args* command-line-options)
      url  (-> opts :options :hostname)]
  (-> (str "https://" url "/get")
      slurp
      (json/decode true)
      pprint/pprint))
