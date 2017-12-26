(spit"logo.svg"(str"<svg xmlns='http://www.w3.org/2000/svg' width='480'
version='1.1' height='480'>"(loop[i 0 R 255 G 255 B 0 o ""](let[r(- 128
i)q(format"fill='#%02x%02x%02x' style='fill-opacity:.06'/>" R G B)a(/ i
12.)b(+ i 80)x(+ 240(* b(Math/cos a)))y(+ 240(* b(Math/sin a)))p(format
"<circle cx='%f' cy='%f' r='%d' "x y r)](if(< i 128)(recur(inc i)(- R 2
)G(+ B 2)(str o p q p"fill='none' stroke='black'/>"))o)))"</svg>"));ugh
