{[clojure-complete "0.2.5" :exclusions [[org.clojure/clojure]]] nil,
 [com.siili/lein-cucumber "1.0.7" :scope "test"]
 {[info.cukes/cucumber-clojure "1.2.4" :scope "test"]
  {[info.cukes/cucumber-core "1.2.4" :scope "test"]
   {[info.cukes/cucumber-html "0.2.3" :scope "test"] nil,
    [info.cukes/cucumber-jvm-deps
     "1.0.5"
     :scope
     "test"
     :exclusions
     [[com.thoughtworks.xstream/xstream]
      [com.googlecode.java-diff-utils/diffutils]]]
    nil,
    [info.cukes/gherkin "2.12.2" :scope "test"] nil}},
  [leiningen-core "2.0.0" :scope "test"]
  {[bultitude "0.1.7" :scope "test"] nil,
   [classlojure "0.6.6" :scope "test"] nil,
   [com.cemerick/pomegranate
    "0.0.13"
    :scope
    "test"
    :exclusions
    [[org.slf4j/slf4j-api]]]
   {[org.apache.maven.wagon/wagon-http "2.2" :scope "test"]
    {[org.apache.httpcomponents/httpclient "4.1.2" :scope "test"]
     {[commons-codec "1.4" :scope "test"] nil},
     [org.apache.httpcomponents/httpcore "4.1.2" :scope "test"] nil,
     [org.apache.maven.wagon/wagon-http-shared4 "2.2" :scope "test"]
     {[commons-io "2.0.1" :scope "test"] nil,
      [commons-logging "1.1.1" :scope "test"] nil,
      [org.jsoup/jsoup "1.6.1" :scope "test"] nil}},
    [org.apache.maven.wagon/wagon-provider-api "2.2" :scope "test"]
    nil,
    [org.apache.maven/maven-aether-provider "3.0.4" :scope "test"]
    {[org.apache.maven/maven-model-builder "3.0.4" :scope "test"]
     {[org.codehaus.plexus/plexus-interpolation "1.14" :scope "test"]
      nil},
     [org.apache.maven/maven-model "3.0.4" :scope "test"] nil,
     [org.apache.maven/maven-repository-metadata "3.0.4" :scope "test"]
     nil,
     [org.codehaus.plexus/plexus-component-annotations
      "1.5.5"
      :scope
      "test"
      :exclusions
      [[junit]]]
     nil},
    [org.sonatype.aether/aether-api "1.13.1" :scope "test"] nil,
    [org.sonatype.aether/aether-connector-file "1.13.1" :scope "test"]
    nil,
    [org.sonatype.aether/aether-connector-wagon "1.13.1" :scope "test"]
    {[org.codehaus.plexus/plexus-classworlds "2.4" :scope "test"] nil,
     [org.codehaus.plexus/plexus-utils "2.0.7" :scope "test"] nil,
     [org.sonatype.sisu/sisu-inject-plexus "2.2.3" :scope "test"]
     {[org.sonatype.sisu/sisu-inject-bean "2.2.3" :scope "test"]
      {[org.sonatype.sisu/sisu-guice
        "3.0.3"
        :classifier
        "no_aop"
        :scope
        "test"
        :exclusions
        [[javax.inject] [aopalliance]]]
       nil}}},
    [org.sonatype.aether/aether-impl "1.13.1" :scope "test"]
    {[org.sonatype.aether/aether-spi "1.13.1" :scope "test"] nil},
    [org.sonatype.aether/aether-util "1.13.1" :scope "test"] nil},
   [useful "0.8.6" :scope "test"]
   {[org.clojure/tools.macro "0.1.1" :scope "test"] nil}}},
 [expectations "2.0.9"] {[junit "4.8.1"] nil},
 [nrepl "0.7.0" :exclusions [[org.clojure/clojure]]] nil,
 [org.clojure/clojure "1.10.1"]
 {[org.clojure/core.specs.alpha "0.2.44"] nil,
  [org.clojure/spec.alpha "0.2.176"] nil},
 [org.clojure/data.json "0.2.5"] nil,
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
