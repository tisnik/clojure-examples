#!/usr/bin/env bb

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

(let [response (slurp "https://httpbin.org/get")
      parsed   (json/decode response true)
      headers  (:headers parsed)
      user-agent (:User-Agent headers)]
  (println user-agent))
