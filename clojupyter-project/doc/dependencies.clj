{[clojure-complete "0.2.5" :exclusions [[org.clojure/clojure]]] nil,
 [incanter "1.9.3"]
 {[incanter/incanter-charts "1.9.3"]
  {[clj-time "0.14.0" :exclusions [[org.clojure/clojure]]]
   {[joda-time "2.9.7"] nil},
   [org.jfree/jfreechart "1.0.19"] {[org.jfree/jcommon "1.0.23"] nil}},
  [incanter/incanter-core "1.9.3"]
  {[net.mikera/core.matrix
    "0.52.0"
    :exclusions
    [[org.clojure/clojure]]]
   {[org.clojure/tools.macro "0.1.5"] nil},
   [net.mikera/vectorz-clj
    "0.44.1"
    :exclusions
    [[org.clojure/clojure]]]
   {[net.mikera/clojure-utils "0.7.0"] nil,
    [net.mikera/vectorz "0.62.0"]
    {[net.mikera/mathz "0.3.0"] nil,
     [net.mikera/randomz "0.3.0"] nil,
     [us.bpsm/edn-java "0.4.6"] nil}},
   [net.sourceforge.parallelcolt/parallelcolt "0.10.1"]
   {[com.github.rwl/JKLU "1.0.0"]
    {[com.github.rwl/AMDJ "1.0.1"] nil,
     [com.github.rwl/BTFJ "1.0.1"] nil,
     [com.github.rwl/COLAMDJ "1.0.1"] nil},
    [com.googlecode.netlib-java/netlib-java "0.9.3"] nil,
    [net.sourceforge.csparsej/csparsej "1.1.1"] nil,
    [net.sourceforge.jplasma/jplasma "1.2.0"]
    {[net.sourceforge.f2j/arpack_combined_all "0.1"] nil,
     [net.sourceforge.jplasma/core-lapack "0.1"] nil},
    [net.sourceforge.jtransforms/jtransforms "2.4.0"]
    {[junit "4.8.2"] nil},
    [net.sourceforge.parallelcolt/optimization "1.0"] nil},
   [org.clojure/math.combinatorics
    "0.1.4"
    :exclusions
    [[org.clojure/clojure]]]
   nil},
  [incanter/incanter-excel "1.9.3"]
  {[org.apache.poi/poi-ooxml "3.16"]
   {[com.github.virtuald/curvesapi "1.04"] nil,
    [org.apache.poi/poi-ooxml-schemas "3.16"]
    {[org.apache.xmlbeans/xmlbeans "2.6.0"]
     {[stax/stax-api "1.0.1"] nil}},
    [org.apache.poi/poi "3.16"]
    {[commons-codec "1.10"] nil,
     [org.apache.commons/commons-collections4 "4.1"] nil}}},
  [incanter/incanter-io "1.9.3"]
  {[net.sf.opencsv/opencsv "2.3"] nil,
   [org.clojure/data.csv "0.1.4" :exclusions [[org.clojure/clojure]]]
   nil,
   [org.danlarkin/clojure-json
    "1.1"
    :exclusions
    [[org.clojure/clojure] [org.clojure/clojure-contrib]]]
   nil},
  [incanter/incanter-latex "1.9.3"]
  {[org.scilab.forge/jlatexmath "1.0.6"]
   {[org.scilab.forge/jlatexmath-font-cyrillic "1.0.6"] nil,
    [org.scilab.forge/jlatexmath-font-greek "1.0.6"] nil}},
  [incanter/incanter-mongodb "1.9.3"]
  {[congomongo
    "0.5.0"
    :exclusions
    [[org.clojure/clojure] [org.clojure/clojure-contrib]]]
   {[org.clojure/data.json "0.2.6"] nil,
    [org.mongodb/mongo-java-driver "2.14.0"] nil}},
  [incanter/incanter-pdf "1.9.3"]
  {[com.lowagie/itext
    "2.1.7"
    :exclusions
    [[org.bouncycastle/bctsp-jdk14]
     [bouncycastle/bcprov-jdk14]
     [bouncycastle/bcmail-jdk14]]]
   nil,
   [org.bouncycastle/bctsp-jdk14 "1.46"]
   {[org.bouncycastle/bcmail-jdk14 "1.46"] nil,
    [org.bouncycastle/bcprov-jdk14 "1.46"] nil}},
  [incanter/incanter-sql "1.9.3"]
  {[clojureql "1.0.5" :exclusions [[org.clojure/clojure]]]
   {[mysql/mysql-connector-java "5.1.17"] nil,
    [org.apache.derby/derby "10.1.1.0"] nil,
    [org.clojure/core.incubator "0.1.4"] nil,
    [org.clojure/java.jdbc "0.2.3"] nil,
    [org.clojure/tools.nrepl "0.2.12"] nil,
    [org.xerial/sqlite-jdbc "3.7.2"] nil,
    [postgresql "8.4-702.jdbc4"] nil}},
  [incanter/incanter-svg "1.9.3"]
  {[org.apache.xmlgraphics/batik-awt-util "1.9.1"]
   {[org.apache.xmlgraphics/xmlgraphics-commons "2.2"]
    {[commons-io "1.3.1"] nil, [commons-logging "1.0.4"] nil}},
   [org.apache.xmlgraphics/batik-dom "1.9.1"]
   {[org.apache.xmlgraphics/batik-css "1.9.1"] nil,
    [org.apache.xmlgraphics/batik-ext "1.9.1"] nil,
    [xalan "2.7.2"] {[xalan/serializer "2.7.2"] nil},
    [xml-apis/xml-apis-ext "1.3.04"] nil,
    [xml-apis "1.3.04"] nil},
   [org.apache.xmlgraphics/batik-svggen "1.9.1"] nil,
   [org.apache.xmlgraphics/batik-util "1.9.1"]
   {[org.apache.xmlgraphics/batik-constants "1.9.1"] nil,
    [org.apache.xmlgraphics/batik-i18n "1.9.1"] nil},
   [org.apache.xmlgraphics/batik-xml "1.9.1"] nil},
  [incanter/incanter-zoo "1.9.3"] nil,
  [swingrepl
   "1.3.0"
   :exclusions
   [[org.clojure/clojure] [org.clojure/clojure-contrib]]]
  nil},
 [nrepl "0.7.0" :exclusions [[org.clojure/clojure]]] nil,
 [org.clojure/clojure "1.10.1"]
 {[org.clojure/core.specs.alpha "0.2.44"] nil,
  [org.clojure/spec.alpha "0.2.176"] nil},
 [venantius/ultra "0.6.0"]
 {[grimradical/clj-semver "0.3.0" :exclusions [[org.clojure/clojure]]]
  nil,
  [io.aviso/pretty "0.1.35"] nil,
  [mvxcvi/puget "1.1.0"]
  {[fipp "0.6.14"] {[org.clojure/core.rrb-vector "0.0.13"] nil},
   [mvxcvi/arrangement "1.1.1"] nil},
  [mvxcvi/whidbey "2.1.0"] {[org.clojure/data.codec "0.1.1"] nil},
  [org.clojars.brenton/google-diff-match-patch "0.1"] nil,
  [robert/hooke "1.3.0"] nil,
  [venantius/glow "0.1.5" :exclusions [[hiccup] [garden]]]
  {[clj-antlr "0.2.3"]
   {[org.antlr/antlr4-runtime "4.5.3"] nil,
    [org.antlr/antlr4 "4.5.3"] nil},
   [instaparse "1.4.1"] nil}}}
