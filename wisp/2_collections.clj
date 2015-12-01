; Examples for Wisp: Homoiconic JS with clojure syntax, s-expressions & macros
;
;  (C) Copyright 2015  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

; Test překladu deklarace různých typů kolekcí do JavaScriptu

; Seznamy
'(1 2 3 4)
'("prvni" "druhy" "treti")
'(:prvni :druhy :treti)

; Vektory
[1 2 3 4]
["prvni" "druhy" "treti"]
[:prvni :druhy :treti]

; Mapa
{"prvni" "first" "druhy" "second" "treti" "third"}

; Mnozina
#{"prvni" "druhy" "treti"}

