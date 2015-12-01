# Examples for Wisp: Homoiconic JS with clojure syntax, s-expressions & macros
#
#  (C) Copyright 2015  Pavel Tisnovsky
#
#  All rights reserved. This program and the accompanying materials
#  are made available under the terms of the Eclipse Public License v1.0
#  which accompanies this distribution, and is available at
#  http://www.eclipse.org/legal/epl-v10.html
#
#  Contributors:
#      Pavel Tisnovsky
#

all:	1_data_types.js 2_collections.js 3_arithmetic_functions.js 4_relop.js

clean:
	rm *.js

%.js:	%.clj
	cat $< | wisp > $@

.PHONY: clean

