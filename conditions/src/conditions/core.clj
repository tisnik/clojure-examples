; Ukázky použití rozhodovacích konstrukcí v programovacím jazyku Clojure
;
; Součást článků:
;
; Řídicí struktury využitelné v programovacím jazyku Clojure
; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/

; Popsány a použity budou následující rozhodovací konstrukce
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; | #  | Konstrukce  | Typ                                 | Stručný popis                                                                               |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; |  1 | if          | speciální forma                     | základní rozhodovací konstrukce, základ pro další makra                                     |
; |  2 | if+do       | dvě speciální formy                 | použito ve chvíli, kdy je nutné do jedné větve či obou větví zapsat více výrazů             |
; |  3 | if-let      | makro                               | kombinace speciálních forem "if" a "let"                                                    |
; |  4 | if-some     | makro                               | kombinace speciálních forem "if" a "let" (test na hodnotu "nil")                            |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; |  5 | and         | makro                               | postupné vyhodnocování předaných výrazů až do chvíle, kdy se vrátí nil či false             |
; |  6 | or          | makro                               | postupné vyhodnocování předaných výrazů až do chvíle, kdy se vrátí true                     |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; |  7 | when        | makro                               | vhodná náhrada za "if" s jedinou větví s více výrazy                                        |
; |  8 | when-not    | makro                               | vhodná náhrada za "if-not" s jedinou větví s více výrazy                                    |
; |  9 | when-let    | makro                               | kombinace speciálních forem "when" a "let"                                                  |
; | 10 | when-some   | makro                               | kombinace speciálních forem "when" (test na hodnotu "nil") a "let"                          |
; | 11 | when-first  | makro                               |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; | 12 | cond        | makro                               | postupné testování podmínek, pokud je podmínka splněna, vrátí se hodnota příslušného výrazu |
; | 13 | cond + else | makro                               | typické použití makra cond s větví :else nebo :default                                      |
; | 14 | condp       | makro                               |
; | 15 | conde       | makro z knihovny clojure.core.logic |
; | 16 | condu       | makro z knihovny clojure.core.logic |
; | 17 | conda       | makro z knihovny clojure.core.logic |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; | 18 | cond->      | makro                               |
; | 19 | cond->>     | makro                               |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; | 20 | case        | makro                               |
; | 21 | case + else | makro                               |
; --------------------------------------------------------------------------------------------------------------------------------------------------------
; | 22 | cond-table  | makro (prozatím bez knihovny)       | nová nestandardní konstrukce se zdrojovým kódem zmíněným na konci druhého článku            |
; --------------------------------------------------------------------------------------------------------------------------------------------------------



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Příprava
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(ns conditions.core)

; Poněkud netradičně je nutné používat funkce a makra ze jmenného prostoru
; "clojure.repl", zejména funkci "doc":
(use 'clojure.repl)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Speciální forma if
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k02
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Všechna makra popsaná v dalším textu jsou založena na využití speciální formy
; if, jejíž varianty si popíšeme v tomto oddílu.

; Nejprve vytvoříme několik jmen navázaných na hodnoty (což je v programovacím
; jazyku Clojure obdoba konstant). Tyto hodnoty budou použity v podmínkách
; uvedených později:
(def x 10)
(def y 20)
(def z 20)

; Poznámka: v naprosté většině skutečných aplikací by se "def" tímto způsobem
; nepoužívalo, nebo alespoň ne frekventovaně, protože to vede k vytvoření
; obdoby hodnot dostupných globálně; v rámci celého jmenného prostoru.

; Základní rozhodovací konstrukce programovacího jazyka Clojure je tvořena
; speciální formou nazvanou "if", která existuje ve dvou variantách - s větví
; then a s oběma větvemi then i else. Tuto speciální formu lze tedy zapsat ve
; zkrácené podobě:
;; (if test větev-then)

; nebo v její plné podobě:
;; (if test větev-then větev-else)

; Bližší informace o této speciální formě získáme přímo z vestavěné nápovědy z
; interaktivní smyčky REPL programovacího jazyka Clojure. Připomeňme si, že
; nápovědu k jakémukoli symbolu je možné zobrazit makrem pojmenovaným "doc".
; Pro speciální formu "if" se tedy zobrazí nápověda takto:
(doc if)

; S tímto výsledkem vypsaným přímo na terminál:
;; -------------------------
;; if
;;   (if test then else?)
;; Special Form
;;   Evaluates test. If not the singular values nil or false,
;;   evaluates and yields then, otherwise, evaluates and yields else. If
;;   else is not supplied it defaults to nil.
;; 
;;   Please see http://clojure.org/special_forms#if

; Speciální formu "if" je možné použít ve formě rozvětvení, což je konstrukce
; známá například z programovacích jazyků C, Pascalu, Pythonu atd. V tomto
; případě se typicky setkáme s funkcemi nebo makry s vedlejším efektem, které
; jsou ve větvi "then" použity: 
(if (< x y)
  (println "x < y"))

; Funkce "println" je v tomto případě zavolána pouze tehdy, pokud je podmínka
; platná. Předchozí podmínka platí, ovšem následující nikoli, proto nebude
; "println" zavolána:
(if (< y x)
  (println "y < x"))

; V programovacím jazyku Clojure a současně i ve všech LISPovských
; programovacích se speciální forma "if" vyhodnocuje a vrátí se výsledek tohoto
; vyhodnocení. Pokud je podmínka splněna, je vrácena hodnota funkce/makra ve
; větvi "then", v opačném případě je vrácena hodnota funkce/makra ve větvi
; "else" nebo "nil" tehdy, pokud větev "else" není vůbec zapsána:
(if (< x y)
  (+ x y))

; Nebo ještě jednodušeji:
(if (< x y)
  :mensi)

; Následující konstrukce vrátí hodnotu "nil", protože podmínka není splněna a
; současně není zapsána větev "else":
(if (< y x)
  (+ x y))

; resp.:
(if (> x y)
  :vetsi)

; Poznámka: podobným způsobem se konstrukce "if" používá například v
; programovacím jazyku Rustu, kde se taktéž jedná o výraz. V Rustu lze tedy
; psát:
;;  fn if_expression(value:i32) {
;;    let value_type =
;;        if value < 0 {
;;            "zaporna"
;;        } else {
;;            if value == 0 {
;;                "nulova"
;;            } else {
;;                "kladna"
;;            }
;;        };
;;    println!("Hodnota {} je {}", value, value_type);
;;  };

; Poznámka: v předchozím příkladu je fakt, že se jedná o zápis výrazu ještě
; zvýrazněn tím, že není použit příkaz "return".



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Použití speciální formy if s oběma větvemi a problematika více volaných funkcí ve větvích
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k03
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Vraťme se však k programovacímu jazyku Clojure. Již jsme si řekli, že je
; podporována i speciální forma "if" s oběma větvemi "then" i "else". Zápis je
; v tomto případě jednoduchý:
(if (< x y)
  (println "x < y")
  (println "x > y"))

; Poznámka: předchozí konstrukce vytiskla (přes vedlejší efekt) zprávu na
; terminál a za všech okolností vrátila hodnotu "nil"

; Ještě lépe je konstrukce rozvětvení patrná na demonstračním příkladu, který
; vrátí hodnotu :mensi nebo :vetsi na základě vyhodnocení podmínky:
(if (< x y)
  :mensi
  :vetsi)

; Konstrukce if je možné v případě potřeby vnořovat:

(if (zero? x)
  :nulove
  (if (neg? x)
    :zaporne
    :kladne))

; Praktičtější příklad – výpočet největšího společného dělitele:

(defn gcd
  [x y]
  (if (= x y)
    x
    (if (> x y)
      (gcd (- x y) y)
      (gcd x (- y x)))))
 
(println (gcd 64 24))

; V tomto případě je však lepší použít konstrukci cond popsanou níže.

; Mnohdy se setkáme s požadavkem, že se v jedné větvi speciální formy "if"
; popř. v obou větvích má použít více funkcí s vedlejším efektem (volání více
; funkcí bez vedlejšího efektu nemá význam). Tento požadavek, který je v
; Algolských programovacích jazycích (C, Pascal, JavaScript, Go, ...) vyřešen s
; využitím programových bloků, by bylo možné otrocky přepsat do jazyka Clojure
; pomocí speciální formy "do":
(if (< x y)
  (do
    (println "x < y")
    :mensi)
  (do
    (println "x > y")
    :vetsi))

; I ke speciální formě "do" (vedle "if" a dalších přibližně patnácti
; speciálních forem patří mezi základní konstrukce Clojure) samozřejmě existuje
; nápověda:
(doc do)

; S výsledkem:
;;   -------------------------
;; do
;;   (do exprs*)
;; Special Form
;;   Evaluates the expressions in order and returns the value of
;;   the last. If no expressions are supplied, returns nil.
;; 
;;   Please see http://clojure.org/special_forms#do



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Makra when a when-not
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k04
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Ve skutečnosti je však výše zmíněná kombinace "if" + "do" velmi špatně
; čitelná, nehledě na to, že se kód začíná ztrácet ve velkém množství kulatých
; závorek. Existuje však i (pro mnoho účelů) lepší řešení. Pokud konstrukce
; "if" obsahuje pouze jedinou větev (předpokládejme nyní pro určité
; zjednodušení, že se jedná o větev "then"), lze namísto "if" použít makro
; nazvané příznačně "when":

(doc when)
;; -------------------------
;; clojure.core/when
;; ([test & body])
;; Macro
;;   Evaluates test. If logical true, evaluates body in an implicit do.

; Následuje ukázka příkladu použití tohoto makra v situaci, kdy se má při
; splnění podmínky vykonat více funkcí (s vedlejším efektem) a navíc se má
; vrátit nějaká hodnota:
(when (< x y)
  (println "----------")
  (println "x < y")
  (println "----------")
  :mensi)

; Nepatrně složitější příklad vracející výsledek funkce +:
(when (< x y)
  (println "----------")
  (println "x < y")
  (println "----------")
  (+ x y))

; V případě, že podmínka splněna není, žádná funkce se nezavolá a makro "when"
; vrátí hodnotu "nil":
(when (> x y)
  (println "x > y")
  :vetsi)

; Pokud je naopak nutné zavolat více funkcí při NEsplnění podmínky, použije se
; namísto makra "when" makro nazvané "when-not":
(doc when-not)
;; -------------------------
;; clojure.core/when-not
;; ([test & body])
;; Macro
;;  Evaluates test. If logical false, evaluates body in an implicit do.

; Opět si ve stručnosti ukažme příklad použití tohoto makra:
(when-not (> x y)
  (println "----------")
  (println "x < y")
  (println "----------")
  (+ x y))

; Popř.:
(when-not (< x y)
  (println "x > y")
  :vetsi)

; Poznámka: pokud vám - podobně jako mnoha dalším programátorům - nevyhovuje
; název makra "when-not", není nic jednoduššího, než si vytvořit vlastní makro
; typicky nazvané "unless":

(defmacro unless [& args] `(when-not ~@args))

; Poznámka: "unless" není náhodně zvolené jméno, protože ho najdeme v mnoha
; interpreterech a překladačích programovacího jazyka Scheme. Příkladem může
; být Racket: https://docs.racket-lang.org/reference/when_unless.html

; Vzhledem k tomu, že "when" je makro, můžeme se podívat na expanzi tohoto
; makra, tedy na programový kód, který je tímto makrem vygenerován:
(macroexpand-1
  '(when (< x y)
    (println "----------")
    (println "x < y")
    (println "----------")
    (+ x y)))

; Z výsledku je patrné, že expanze je v tomto případě přímočaré rozepsání na
; základní speciální formy "if" a "do":
;; (if
;; (< x y)
;; (do (println "----------") (println "x < y") (println "----------") (+ x y)))

; Totéž můžeme vyzkoušet i pro makro "when-not":
(macroexpand-1
  '(when-not (< x y)
    (println "----------")
    (println "x < y")
    (println "----------")
    (+ x y)))

; Nyní je výsledek expanze makra zajímavější, protože větev "then" je
; vygenerována, ovšem obsahuje pouze "nil", zatímco větev "else" je umístěna do
; speciální formy "do" (což je logické, protože potřebujeme zavolat více
; funkcí):
;; (if
;;  (< x y)
;;  nil
;;  (do (println "----------") (println "x < y") (println "----------") (+ x y)))

; Zajímavější je situace, která nastane při expanzi makra "unless", které se
; expanduje na jiné makro, konkrétně na "when-not":
(defmacro unless [& args] `(when-not ~@args))

; Ostatně se sami podívejme, jak vypadá výsledek jedné expanze:
(macroexpand-1
  '(unless (< x y)
    (println "----------")
    (println "x < y")
    (println "----------")
    (+ x y)))

; Výsledkem je pouhá náhrada makra "unless" za "when-not":
;; (clojure.core/when-not
;;  (< x y)
;;  (println "----------")
;;  (println "x < y")
;;  (println "----------")
;;  (+ x y))

; Úplnou expanzi si můžeme zobrazit pomocí "macroexpand":
(macroexpand
  '(unless (< x y)
    (println "----------")
    (println "x < y")
    (println "----------")
    (+ x y)))

; S tímto výsledkem:
;; (if
;;  (< x y)
;;  nil
;;  (do (println "----------") (println "x < y") (println "----------") (+ x y)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Kombinace speciálních forem if a let
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k05
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Prozatím jsme si ukázali zejména speciální formu "if" a způsoby nahrazení
; této formy v případě, že je zapotřebí vykonat jednu vícekrokovou větev.
; Náhrada spočívá v použití maker "when" a "when-not". Existují však i další
; velmi často používané konstrukce, například kombinace speciální formy "if" s
; další speciální formou "let" nazvaná příznačně "if-let":
(doc if-let)
;; -------------------------
;; clojure.core/if-let
;; ([bindings then] [bindings then else & oldform])
;; Macro
;;   bindings => binding-form test
;; 
;;   If test is true, evaluates then with binding-form bound to the value of 
;;   test, if not, yields else

; Podívejme se nyní na způsob použití tohoto makra. Předpokládejme, že budeme
; chtít vytvořit skript, který se zeptá na jméno uživatele a pokud uživatel
; jméno skutečně zadá, vypíše se řetězec "Hello", za nímž následuje zapsané
; jméno. Pokud ovšem uživatel pouze stiskne klávesu "Enter", nic se neprovede.
; Takový skript by bylo možné zapsat následujícím způsobem:
(let [name (read-line)]
  (if (not-empty name)
    (println "Hello!" name)))

; Výše uvedený kód je však zbytečně komplikovaný. Můžeme ho zkrátit na použití
; makra "if-let" a těla "then" s funkcí "println":
(if-let [name (not-empty (read-line))]
  (println "Hello!" name))

; Pro zajímavost se nyní podívejme na plnou expanzi předchozího skriptu, který
; je založen na makru "if-let":
(macroexpand
  '(if-let [name (not-empty (read-line))]
    (println "Hello!" name)))

; Z výsledku je patrné, že byla použita automaticky vytvořená lokální proměnná:
;; (let*
;;  [temp__5733__auto__ (not-empty (read-line))]
;;  (if
;;   temp__5733__auto__
;;   (clojure.core/let [name temp__5733__auto__] (println "Hello!" name))
;;   nil))

; Otestujme si, zda "if-let" vrací nějakou hodnotu:

(if-let [name (not-empty (read-line))]
  (str "Hello! " name))

; Úplná forma makra "if-let" s větví "else" vypadá následovně:
(if-let [name (not-empty (read-line))]
  (str "Hello! " name)
  :nothing)

; Tento výraz vrátí buď řetězec "Hello! (zadaný řetězec)" nebo hodnotu "nil".

; Pozor: toto makro obsahuje jednu past: pokud přistoupíte k lokálnímu jménu z
; větve "else", vrátí se nikoli hodnota (ta není navázána), ale identifikátor
; ve stylu "Name= clojure.core$name@14df1ec3"
(if-let [name (not-empty (read-line))]
  (str "Hello! " name)
  (str "Name= " name))

; Úprava je v tomto případě snadná, i když ne na první pohled zřejmá: musíme
; použít unikátní jméno namísto existující funkce "name":
(if-let [x (not-empty (read-line))]
  (str "Hello! " x)
  (str "Name= " x))


; Podobně jako ke speciální formě "if" existuje varianta "if-let", je k makru "when" vytvořena jeho obdoba nazvaná "when-let" (což je opět makro):
(doc when-let)
;; -------------------------
;; clojure.core/when-let
;; ([bindings & body])
;; Macro
;;  bindings => binding-form test
;;
;;  When test is true, evaluates body with binding-form bound to the value of test

; Opět se podívejme na příklad použití tohoto makra. Konkrétně se bude jednat o
; přepis následujícího skriptu (nepatrně upravený skript z předchozích
; kapitol):
(let [name (read-line)]
  (when (not-empty name)
    (print "Hello! ")
    (println name)
    name)) ; návratová hodnota

; Na jeho kratší variantu postavené právě na makru "when-let":
(when-let [name (not-empty (read-line))]
  (print "Hello! ")
  (println name)
  name) ; návratová hodnota

; Expanze tohoto makra ukáže, že se v expandovaném kódu používá makro "when":
(macroexpand-1
  '(when-let [name (not-empty (read-line))]
    (print "Hello! ")
    (println name)
    name))

; Nyní již expanze není příliš přímočará z důvodu použití generovaných proměnných:
;; (clojure.core/let
;;  [temp__5735__auto__ (not-empty (read-line))]
;;  (clojure.core/when
;;   temp__5735__auto__
;;   (clojure.core/let
;;    [name temp__5735__auto__]
;;    (print "Hello! ")
;;    (println name)
;;    name)))

; varianta s "if-some" a "when-some" (viz další blok)
(if-some [x (not-empty (read-line))]
  (str "Hello! " x)
  :nic)

(macroexpand
  '(if-some [x (not-empty (read-line))]
    (str "Hello! " x)
    :nic))

(when-some [name (not-empty (read-line))]
  (print "Hello! ")
  (println name)
  name) ; návratová hodnota

(macroexpand
  '(when-some [name (not-empty (read-line))]
    (print "Hello! ")
    (println name)
    name)) ; návratová hodnota



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Makra "if-some" a "when-some"
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k06
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Krátce se zmiňme o makrech "if-some" a "when-some". Jedná se o obdobu již
; výše popsaných maker "if-let" a "when-let", ovšem s jednou podstatnou
; výjimkou: makra "if-some" a "when-some" testují, zda je přiřazovaná hodnota
; rovna "nil" či nikoli zatímco makra "if-let" a "when-let" rozlišují mezi
; pravdivými a nepravdivými hodnotami. V Clojure jsou přitom prakticky všechny
; hodnoty považovány za pravdivé (a to včetně nuly či prázdného řetězce),
; jedinou výjimkou jsou hodnoty "false" a "nil", které jsou považovány za
; nepravdu.  Rozdíl mezi makry "if-some"/"when-some" a "if-let"/"when-let" si
; ukážeme na poněkud umělém příkladu (umělém z toho důvodu, že prakticky nikdy
; není nutné rozlišovat mezi "false" a "nil", což jsou jediné hodnoty, v nichž
; se funkce maker odlišuje):

(doc if-some)
; -------------------------
; clojure.core/if-some
; ([bindings then] [bindings then else & oldform])
; Macro
;   bindings => binding-form test
; 
;    If test is not nil, evaluates then with binding-form bound to the
;    value of test, if not, yields else
; nil

(doc when-some)
; -------------------------
; clojure.core/when-some
; ([bindings & body])
; Macro
;   bindings => binding-form test
; 
;    When test is not nil, evaluates body with binding-form bound to the
;    value of test
; nil

; Zadefinujeme dva vektory a budeme zkoumat, zda jsou všechny jejich prvky
; kladné či nikoli:
(def values1 [-2 -1 0 1 2])
(def values2 [1 2 3])

; Použití makra "if-let" pro vektor, v němž se nachází nula i záporné prvky:
(if-let [x (every? pos? values1)]
  (println "Found")
  (println "Not found"))
; Not found
; nil

; Použití makra "if-let" pro vektor, v němž se nachází jen kladné prvky:
(if-let [x (every? pos? values2)]
  (println "Found")
  (println "Not found"))
; Found
; nil

; Použití makra "if-some" pro vektor, v němž se nachází nula i záporné prvky:
(if-some [x (every? pos? values1)]
  (println "Found")
  (println "Not found"))
; Found
; nil

; Poznámka: právě zde se "if-some" chová jinak než "if-let", a to z toho
; důvodu, že "false" není rovno "nil".

; Použití makra "if-some" pro vektor, v němž se nachází jen kladné prvky:
(if-some [x (every? pos? values2)]
  (println "Found")
  (println "Not found"))
; Found
; nil



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Použití maker "and" a "or" pro řízení toku programu
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k07
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; V některých programovacích jazycích je zvykem nahrazovat některé rozhodovací
; konstrukce (resp. přesněji řečeno podmínky) s využitím Booleovských operátorů
; "and" a "or". Podobný koncept můžeme použít i v jazyce Clojure s využitím
; maker nazvaných taktéž "and" a "or", které jsou n-ární, tj. akceptují
; libovolný počet parametrů:
(doc and)
;;  -------------------------
;; clojure.core/and
;; ([] [x] [x & next])
;; Macro
;;   Evaluates exprs one at a time, from left to right. If a form
;;   returns logical false (nil or false), and returns that value and
;;   doesn't evaluate any of the other expressions, otherwise it returns
;;   the value of the last expr. (and) returns true.

(doc or)
;; -------------------------
;; clojure.core/or
;; ([] [x] [x & next])
;; Macro
;;   Evaluates exprs one at a time, from left to right. If a form
;;   returns a logical true value, or returns that value and doesn't
;;   evaluate any of the other expressions, otherwise it returns the
;;   value of the last expression. (or) returns nil.
;; 

; Předností použití těchto maker může být fakt, že se složitější podmínky a
; současně i větev, která se má vykonat, mohou zapsat jediným výrazem - obě
; makra totiž akceptují proměnný počet parametrů.

; Následuje příklad použití těchto dvou maker pro implementaci funkce, která
; vrátí znaménko čísla, které je této funkci předáno. Pro kladná čísla se vrátí
; +1, pro záporná čísla -1 a pro nulu nula:
(defn sgn
  [x]
  (or (and (> x 0) +1)
      (and (< x 0) -1)
      0))

; Otestování funkcionality takto definované funkce:
(doseq [value [-100 -1 0 1 100]]
        (println (sgn value)))

; S výsledky:
;; -1
;; -1
;; 0
;; 1
;; 1

; Alternativní zápis s predikáty namísto porovnání hodnoty "x" s nulou:
(defn sgn-2
  [x]
  (or (and (pos? x) +1)
      (and (neg? x) -1)
      0))

; Opětovné otestování funkcionality takto upravené funkce:
(doseq [value [-100 -1 0 1 100]]
        (println (sgn-2 value)))

; Se shodnými výsledky, jako tomu bylo v předchozím příkladu:
;; -1
;; -1
;; 0
;; 1
;; 1



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Rozhodovací konstrukce založená na makru "cond"
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k08
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Všechny rozhodovací konstrukce popsané v předchozích kapitolách prováděly
; rozvětvení toku programu na základě vyhodnocení jediné podmínky. Ovšem v
; praxi se velmi často setkáme s nutností rozhodovat se na základě většího
; množství podmínek popř. na základě většího množství hodnot (a obecně pak na
; základě pattern matchingu, což si ukážeme příště). Pokud je nutné provést
; rozhodnutí na základě více podmínek, nabízí se využití makra nazvaného
; "cond", které se mj. objevilo (i když jinak zapisované) už v prvních verzích
; LISPu:

(doc cond)
;; -------------------------
;; clojure.core/cond
;; ([& clauses])
;; Macro
;;   Takes a set of test/expr pairs. It evaluates each test one at a
;;   time.  If a test returns logical true, cond evaluates and returns
;;   the value of the corresponding expr and doesn't evaluate any of the
;;  other tests or exprs. (cond) returns nil.
;; nil

; Tomuto makru se předávají dvojice test+výraz. Pokud je test splněn, je
; vrácena hodnota příslušného výrazu. Poslední test bývá zapsán formou symbolu,
; který se vždy vyhodnotí na pravdu – což je vlastně jakýkoli symbol rozdílný
; od "false" nebo "nil". Typicky se používá symbol ":else", ovšem někteří
; vývojáři dávají přednost ":default" (takže se jedná o céčkaře nebo Javisty
; :-).

; Poznámka: v programovacím jazyce Clojure je "cond" realizováno formou makra,
; zatímco v některých implementacích Scheme se jedná o speciální formu. Jediný
; podstatný rozdíl je, že v Clojure si můžeme zobrazit expanzi tohoto makra
; (což si ostatně ukážeme).

(defn sgn-3
  [x]
  (cond (pos? x) 1
        (neg? x) -1
        true 0))

; Otestování funkcionality takto upravené funkce:
(doseq [value [-100 -1 0 1 100]]
        (println (sgn-3 value)))

; Poslední test se ovšem většinou zapisuje symbolem ":else":
(defn sgn-4
  [x]
  (cond (pos? x)  1
        (neg? x) -1
        :else     0))

; nebo ":default":
(defn sgn-5
  [x]
  (cond (pos? x)  1
        (neg? x) -1
        :default  0))

; Opětovné otestování funkcionality takto upravené funkce:
(doseq [value [-100 -1 0 1 100]]
        (println (sgn-4 value)))

; Přepis funkce pro výpočet největšího společného dělitele:
(defn gcd-2
  [x y]
  (cond
    (= x y) x
    (> x y) (gcd-2 (- x y) y)
    :else   (gcd-2 x (- y x))))

; S otestováním:
(println (gcd-2 64 24))
(println (gcd-2 123456 6543216))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Plná expanze makra cond
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k09
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Pokusme se nyní výraz s makrem cond expandovat, abychom zjistili, jaký kód
; bude vlastně přeložen do bajtkódu:

(macroexpand
  '(cond (pos? x)  1
         (neg? x) -1
         :else     0))

; Výsledkem expanze bude:
;; (if (pos? x) 1 (clojure.core/cond (neg? x) -1 :else 0))

; To je zajímavé, protože toto makro volá samo sebe, což funkce "macroexpand"
; nedokáže korektně zpracovat. Namísto této funkce bychom tedy potřebovali
; plnou (rekurzivní) expanzi. Ta je zajištěna s využitím "macroexpand-all" z
; balíčku "clojure.walk", který je nejdříve nutné načíst:
(use 'clojure.walk)

; Nyní se již můžeme pokusit o plnou expanzi makra cond použitého ve výrazu:
(macroexpand-all
  '(cond (pos? x)  1
         (neg? x) -1
         :else     0))

; Nyní již výsledek bude skutečně obsahovat pouze základní funkce, speciální
; formy a atomické hodnoty (tedy takové symboly, které budou překládány do
; bajtkódu):
;; (if (pos? x) 1 (if (neg? x) -1 (if :else 0 nil)))

; Pro zajímavost si vyzkoušejme expanzi složitějšího výrazu, který převádí
; bodové ohodnocení na známky:
(let [grade 85]
  (cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"
    :else         "F"))

; Expanzi (prozatím neúplnou) makra "cond" zobrazíme následovně:
(macroexpand
  '(cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"
    :else "F"))

; Samotný expandovaný kód vypadá takto:
;; (if
;;  (>= grade 90)
;;  "A"
;;  (clojure.core/cond
;;   (>= grade 80)
;;   "B"
;;   (>= grade 70)
;;   "C"
;;   (>= grade 60)
;;   "D"
;;   :else
;;   "F"))

; Úplná expanze:
(macroexpand-all
  '(cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"
    :else "F"))

; Z expandovaného makra je patrné, že se jedná o sérii vnořených speciálních forem "if":
;; (if (>= grade 90)
;;     "A"
;;     (if (>= grade 80)
;;         "B"
;;         (if (>= grade 70)
;;             "C"
;;             (if (>= grade 55)
;;                 "D"
;;                 (if :else
;;                     "F"
;;                     nil)))))

; Poznámka: výše uvedený kód byl ručně upraven tak, aby byl dobře čitelný.



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sekvence testů prováděná makrem condp
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k10
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Výše popsané makro "cond" je velmi univerzální, protože každý test (kterých
; může být libovolné množství) je realizován plnohodnotným predikátem, tj.
; funkcí, na základě jejíž (pravdivostní) návratové hodnoty se rozhoduje,
; jestli se má provést příslušná větev či zda se má vyzkoušet další test. Ve
; výsledku je toto makro expandováno na vnořené speciální formy "if", což jsme
; ostatně mohli vidět v předchozí kapitole. Ovšem mnohdy takovou univerzálnost
; nepotřebujeme a naopak vyžadujeme, aby se výsledek nějakého výrazu porovnal
; se sekvencí známých hodnot. Taková konstrukce, která je v C, C++ či Javě
; realizována přes "switch", se v programovacím jazyku Clojure zapisuje s
; využitím makra nazvaného "condp":

(doc condp)
; -------------------------
; clojure.core/condp
; ([pred expr & clauses])
; Macro
;   Takes a binary predicate, an expression, and a set of clauses.
;   Each clause can take the form of either:
; 
;   test-expr result-expr
; 
;   test-expr :>> result-fn
; 
;   Note :>> is an ordinary keyword.
; 
;   For each clause, (pred test-expr expr) is evaluated. If it returns
;   logical true, the clause is a match. If a binary clause matches, the
;   result-expr is returned, if a ternary clause matches, its result-fn,
;   which must be a unary function, is called with the result of the
;   predicate as its argument, the result of that call being the return
;   value of condp. A single default expression can follow the clauses,
;   and its value will be returned if no clause matches. If no default
;   expression is provided and no clause matches, an
;   IllegalArgumentException is thrown.

; Z popisu je zřejmé, že je nutné uvést část výrazu, do kterého se postupně
; doplňují hodnoty z testů v jednotlivých větvích - skutečně se tedy jedná o
; obdobu "case" z C či Javy. Poslední větev pochopitelně žádnou hodnotu pro
; otestování neobsahuje.

; Podívejme se nyní na základní způsob použití tohoto makra. Na základě
; předaného řetězce se rozhodneme, jaká hodnota se vrátí (jedná se o primitivní
; transformaci, která by se v reálném programu realizovala přes mapu):

(let [value (read-line)]
  (condp = value
      "one"   1
      "two"   2
      "three" 3
      "four"  4
      "five"  5
              "unknown value"))

; Pokud je na standardní vstup (do terminálu) zapsán jeden z pěti známých
; řetězců, provede se jeho převod na číselnou hodnotu. V opačném případě se
; vrátí řetězec "unknown value".

; V případě, že poslední výraz neuvedeme, dojde při zápisu neznámého vstupu k
; pádu (resp. přesněji řečeno k vyhození výjimky):
(let [value (read-line)]
  (condp = value
      "one"   1
      "two"   2
      "three" 3
      "four"  4
      "five"  5))

; Execution error (IllegalArgumentException) at user/eval6625 (REPL:2).
; No matching clause: foobarbaz

; Samozřejmě namísto konstant ve větvích můžeme použít nějaký složitější výraz:

(let [value (read-line)]
  (condp = value
      "one"   (+ 0 1)
      "two"   (+ 1 1)
      "three" 3
      "four"  (* 2 2)
      "five"  5
              (str "unexpected value, \"" value \")))

; Výrazy mohou být použity i v testovaných hodnotách:

(let [value (read-line)]
  (condp = value
      "one"           (+ 0 1)
      (str "t" "wo")  (+ 1 1)
      (str "t" "ree") 3
      "four"          (* 2 2)
      "five"          5
                      (str "unexpected value, \"" value \")))

; Poznámka: pro jednoduchost jsou všechny příklady z této kapitoly dosti umělé;
; typicky "školní".

; Poněkud praktičtější příklad, který porovná dvě hodnoty:
(defn sgn-6
  [x]
  (condp apply [x 0]
    = 0
    < -1
    > 1))

; Otestování:
(println (sgn-6 -100))
(println (sgn-6 -1))
(println (sgn-6 0))
(println (sgn-6 100))

; Tato funkce de facto postupně provádí testy:
(apply = [x 0])
(apply < [x 0])
(apply > [x 0])



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Aplikace funkce namísto vyhodnocení výrazu ve větvi
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k11
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Existuje ještě jedna forma volání makra "condp". Tato forma má v každé větvi
; tři výrazy: podmínku, nějaký "keyword" (začínající na dvojtečku) a funkci. V
; případě, že je podmínka splněna, zavolá se příslušná funkce na výsledek
; predikátu (testu). Tato funkce tedy musí být unární, protože neexistuje
; možnost zápisu druhého či dalšího parametru funkce (samozřejmě však můžete v
; případě potřeby použít uzávěr):

(condp some [1 2 3 4]
  #{0 6 7} :>> inc
  #{4 5 9} :>> dec
  #{1 2 3} :>> #(+ % 3))

; Při expanzi se postupně provádí tyto testy až do doby, kdy nějaký test
; (predikát) vrátí hodnotu rozdílnou od "false" či "nil":
(some #{0 6 7} [1 2 3 4])
(some #{4 5 9} [1 2 3 4])
(some #{1 2 3} [1 2 3 4])

; Ve skutečnosti je již druhý test úspěšný:
(some #{4 5 9} [1 2 3 4])
; 4

; Následně je vrácena hodnota výrazu:
(dec 4)
; 3

; Poznámka: tento opět dosti umělý demonstrační příklad byl získán z adresy
; https://clojuredocs.org/clojure.core/condp. Popravdě jsem prozatím tuto
; variantu v produkčním kódu nikdy nepoužil.



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Ukázka expanze makra condp
;;
;; https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/#k12
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Zkusme si nyní zobrazit expanzi výše popsaného makra "condp". Expandovat
; budeme výraz pro převod vybraných řetězců na celá čísla:
(def value "two")

(condp = value
    "one"   1
    "two"   2
    "three" 3
    "four"  4
    "five"  5
            "unknown value")

; Provedeme expanzi makra nám již známou funkcí "macroexpand":

(macroexpand
  '(condp = value
      "one"   1
      "two"   2
      "three" 3
      "four"  4
      "five"  5
              "unknown value"))

; Z výsledku je patrné, že se provedla expanze (opět) na vnořené speciální
; formy "if":
;; (let*
;;   [pred__6764 clojure.core/= expr__6765 user/value]
;;   (if (pred__6764 "one" expr__6765)
;;       1
;;       (if (pred__6764 "two" expr__6765)
;;           2
;;           (if (pred__6764 "three" expr__6765)
;;               3
;;               (if (pred__6764 "four" expr__6765)
;;                   4
;;                   (if (pred__6764 "five" expr__6765) 5 "unknown value"))))))

; Povšimněte si použití pomocné proměnné, která je zde důležitá, protože
; zabraňuje tomu, aby se výraz vyhodnocoval vícekrát za sebou.

; Poznámka: výsledek byl opět ručně přeformátován do čitelnější podoby.

; Zajímavější je expanze těla funkce "sgn" realizované makrem "condp":
(macroexpand-1
  '(condp apply [x 0]
     = 0
     < -1
     > 1))

; Z expandovaného makra je patrné, že se explicitně vytvořil i kód pro vyhození
; výjimky, zatímco v předchozím příkladu se neznámá hodnota řešila ve větvi
; "else" nejvnitřnější speciální formy "if":
;; (clojure.core/let [pred__6768 apply
;;                    expr__6769 [x 0]]
;;  (if (pred__6768 = expr__6769)
;;      0
;;      (if (pred__6768 < expr__6769)
;;          -1
;;          (if (pred__6768 > expr__6769)
;;              1
;;              (throw (java.lang.IllegalArgumentException.
;;                (clojure.core/str "No matching clause: " expr__6769)))))))
;; 



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Druhá část
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; V dnešním článku dokončíme téma, kterému jsme se věnovali minule
; [https://www.root.cz/clanky/ridici-struktury-vyuzitelne-v-programovacim-jazyku-clojure/].
; Nejprve si popíšeme některá další (a nutno říci, že velmi často používaná)
; makra nabízená standardní knihovnou programovacího jazyka Clojure. Jedná se
; především o makra nazvaná cond->, cond->> (což jsou varianty makra cond, které
; již dobře známe), dále pak o makro if-not a v neposlední řadě o interně dosti
; složité makro nazvané příhodně case. To však není zdaleka vše, protože existují
; i další makra určená pro řízení toku programu. Především se jedná o makra
; nabízená ve jmenném prostoru clojure.core.logic, zejména o makra nazvaná conde,
; condu a conda. Asi nebude velkým překvapením, že se opět jedná o varianty
; standardního makra cond.

; Ještě zajímavější jsou však makra z vybraných nestandardních knihoven, zejména
; pak velmi povedené makro pojmenované příznačně cond-table, jehož autorem je
; Daniel Gregoire, s jehož využitím je možné vytvářet i složité rozhodovací
; tabulky (což jsou de facto dvourozměrné rozhodovací struktury, které možnosti
; jednorozměrných struktur dosti podstatným způsobem zobecňují). Zmíníme se i o
; složitějších rozhodovacích strukturách nabízených například knihovnou
; https://github.com/clojureman/special better-cond, cond-let atd.


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Makro cond->
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; První makro s nímž se dnes setkáme a které nám umožňuje zapsat rozvětvení na základě vyhodnocovaných podmínek, se jmenuje cond->:

(doc cond->)
-------------------------
clojure.core/cond->
;; ([expr & clauses])
;; Macro
;;   Takes an expression and a set of test/form pairs. Threads expr (via ->)
;;   through each form for which the corresponding test
;;   expression is true. Note that, unlike cond branching, cond-> threading does
;;   not short circuit after the first true test expression.

Toto na první pohled poněkud neobvyklé pojmenování nám naznačuje, že se bude jednat o konstrukci, která je založena na takzvaném threading makru. Samotné threading makro je přitom jednou z nejelegantnějších konstrukcí programovacího jazyka Clojure vůbec:

(doc ->)
;; -------------------------
;; clojure.core/->
;; ([x & forms])
;; Macro
;;   Threads the expr through the forms. Inserts x as the
;;   second item in the first form, making a list of it if it is not a
;;   list already. If there are more forms, inserts the first form as the
;;   second item in second form, etc.

; Toto makro nám umožňuje zapisovat sérii funkcí, mezi nimiž se předávají parametry podobně, jako je tomu v klasické koloně. Samotný zápis je přitom velmi stručný a obejde se bez řady závorek:

(-> 1
    inc
    println)
; 2

(-> (range 1 10)
    count
    println)
; 9

; Volat je ovšem možné i funkce s větším množstvím parametrů, včetně metod:
(-> "Hello world."
    .toUpperCase
    (.replace "." "!")
    println)
; HELLO WORLD!

(-> "Hello world."
    .toUpperCase
    (.replace "." "!")
    (.split " ")
    first
    println)
; HELLO

; Expanze tohoto makra vede k zápisu s velkým množstvím závorek tak typickým pro klasické LISPovské jazyky bez tohoto makra:
(macroexpand
  '(-> "Hello world."
      .toUpperCase
      (.replace "." "!")
      (.split " ")
      first
      println))

; S výsledkem:
; (println (first (.split (.replace (.toUpperCase "Hello world.") "." "!") " ")))

; Vraťme se však k makru cond->, které je odvozené od makra cond. Jeho činnost je následující: postupně jsou testovány jednotlivé podmínky (sudé parametry) a pokud je podmínka splněna, je do výrazu za ní (liché parametry) dosazen první parametr makra, podobně jako je tomu u výše zmíněného threading makra.

; V následujícím úryvku kódu se pokoušíme převést obsah řetězce na číselnou hodnotu, ovšem s testem, zda je vstupní hodnota skutečně řetězcem. S využitím makra cond-> se samotné x (tedy reference na převáděnou hodnotu) nemusí opakovat v podmínce i v převodní funkci:
(let [x "42"] 
  (cond-> x 
    (string? x) (Integer.)))
; 42

; Poněkud umělý příklad, který je na makru cond-> postaven, může testovat hodnotu parametru x a následně na základě hodnoty tohoto parametru provádí operace (inc x), (/ x 2) a (dec x):
(defn machina
  [x]
  (cond-> x 
    (odd? x)  inc
    (even? x) (/ 2)
    (zero? x) dec))

; Výše uvedenou funkci si otestujeme na vstupních hodnotách od nuly do deseti:
(doseq [x (range 0 11)]
  (println x (machina x)))

; S těmito výsledky:
; 0 -1
; 1 2
; 2 1
; 3 4
; 4 2
; 5 6
; 6 3
; 7 8
; 8 4
; 9 10
; 10 5



;; 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Na závěr se ještě zmiňme o speciálních formách programovacího jazyka Clojure.
; V tomto článku jsme se setkali se čtyřmi speciálními formami (jsou uvedeny na
; začátku tabulky), ovšem pro úplnost si je vypišme všechny:
;
; -------------------------------------------------------------------------------------------------------------
; | #  | Speciální forma | Stručný popis speciální formy                                                      |
; -------------------------------------------------------------------------------------------------------------
; |  1 | def             | definice nového jména navázaného na hodnotu                                        |
; |  2 | if              | podmíněné vyhodnocení prvního či druhého podvýrazu na základě vyhodnocené podmínky |
; |  3 | do              | vyhodnocení více výrazů, vrátí se návratová hodnota posledního z nich              |
; |  4 | let             | blok, na který je navázána nová lokální proměnná či proměnné                       |
; |  5 | quote           | zakazuje vyhodnocení podvýrazu (tedy seznamu)                                      |
; |  6 | var             | vrací objekt typu Var (nikoli jeho hodnotu), varianta quote                        |
; |  7 | fn              | definice (anonymní) funkce nebo uzávěru                                            |
; |  8 | loop            | použito pro konstrukci smyčky - cíl pro recur                                      |
; |  9 | recur           | skok na začátek smyčky s novými hodnotami navázanými na jména v recur              |
; | 10 | throw           | vyhození výjimky                                                                   |
; | 11 | try             | vyhodnocení výrazů se zachycením výjimky                                           |
; | 12 | monitor-enter   | nízkoúrovňové synchronizační primitivum                                            |
; | 13 | monitor-exit    | nízkoúrovňové synchronizační primitivum                                            |
; | 14 | .               | přístup k metodám a atributům objektu                                              |
; | 15 | new             | konstrukce instance třídy                                                          |
; | 16 | set!            | nastavení hodnoty lokální proměnné v aktuálním vláknu                              |
; -------------------------------------------------------------------------------------------------------------

; Poněkud odlišná je sada speciálních forem v jazyku Scheme (což je taktéž
; LISPovský programovací jazyk):
;
; -------------------------------------------------------------------------------------------------------------
; | #  | Speciální forma | Stručný popis speciální formy                                                      |
; -------------------------------------------------------------------------------------------------------------
; |  1 | lambda          | vytvoření anonymní funkce nebo uzávěru                                             |
; |  2 | define          | definice nové proměnné (může jít i o funkci)                                       |
; |  3 | quote           | zakazuje vyhodnocení podvýrazu (tedy seznamu)                                      |
; |  4 | set!            | změna hodnoty proměnné                                                             |
; |  5 | let             | blok, na který je navázána nová lokální proměnná či proměnné                       |
; |  6 | let*            | podobné "let", ovšem umožňuje při inicializaci proměnných použít proměnné nalevo   |
; |    |                 | (nahoře) od právě deklarované proměnné                                             |
; |  7 | letrec          | podobné "let", ovšem navíc je možné se při inicializaci proměnných rekurzivně      |
; |    |                 | odkazovat na další proměnné                                                        |
; |  8 | letrec*         | kombinace "let*" a "letrec"                                                        |
; |  9 | begin           | umožňuje definovat blok s více výrazy, které se postupně vyhodnotí                 |
; | 10 | if              | podmíněné vyhodnocení prvního či druhého podvýrazu na základě vyhodnocené podmínky |
; | 11 | cond            | vícenásobné rozvětvení (vyhodnocení podvýrazů)                                     |
; | 12 | case            | rozeskok na základě hodnoty vyhodnoceného podvýrazu                                |
; | 13 | when            | pokud je podmínka splněna, vyhodnotí všechny podvýrazy                             |
; | 14 | unless          | pokud podmínka není splněna, vyhodnotí všechny podvýrazy                           |
; | 15 | and             | zkrácené vyhodnocení logického součinu                                             |
; | 16 | or              | zkrácené vyhodnocení logického součtu                                              |
; | 17 | do              | zápis iterace s inicializací logických proměnných i s jejich změnou v každé iteraci|
; -------------------------------------------------------------------------------------------------------------

; Poznámka: povšimněte si odlišných významů některých speciálních forem.
; Zejména se to týká formy "do", jejíž význam je v Clojure odlišný od Scheme.
; Taktéž formy "cond", "case", "and" a "or" ze Scheme jsou v Clojure definovány
; formou maker, namísto "when-not" se používá "unless" atd.



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Příloha: zdrojové kódy výše popsaných a použitých maker
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(source if-let)
;; (defmacro if-let
;;   "bindings => binding-form test
;; 
;;   If test is true, evaluates then with binding-form bound to the value of 
;;   test, if not, yields else"
;;   {:added "1.0"}
;;   ([bindings then]
;;    `(if-let ~bindings ~then nil))
;;   ([bindings then else & oldform]
;;    (assert-args
;;      (vector? bindings) "a vector for its binding"
;;      (nil? oldform) "1 or 2 forms after binding vector"
;;      (= 2 (count bindings)) "exactly 2 forms in binding vector")
;;    (let [form (bindings 0) tst (bindings 1)]
;;      `(let [temp# ~tst]
;;         (if temp#
;;           (let [~form temp#]
;;             ~then)
;;           ~else)))))


(source if-some)
;; (defmacro if-some
;;   "bindings => binding-form test
;; 
;;    If test is not nil, evaluates then with binding-form bound to the
;;    value of test, if not, yields else"
;;   {:added "1.6"}
;;   ([bindings then]
;;    `(if-some ~bindings ~then nil))
;;   ([bindings then else & oldform]
;;    (assert-args
;;      (vector? bindings) "a vector for its binding"
;;      (nil? oldform) "1 or 2 forms after binding vector"
;;      (= 2 (count bindings)) "exactly 2 forms in binding vector")
;;    (let [form (bindings 0) tst (bindings 1)]
;;      `(let [temp# ~tst]
;;         (if (nil? temp#)
;;           ~else
;;           (let [~form temp#]
;;             ~then))))))


(source and)
;; (defmacro and
;;   "Evaluates exprs one at a time, from left to right. If a form
;;   returns logical false (nil or false), and returns that value and
;;   doesn't evaluate any of the other expressions, otherwise it returns
;;   the value of the last expr. (and) returns true."
;;   {:added "1.0"}
;;   ([] true)
;;   ([x] x)
;;   ([x & next]
;;    `(let [and# ~x]
;;       (if and# (and ~@next) and#))))


(source or)
;; (defmacro or
;;   "Evaluates exprs one at a time, from left to right. If a form
;;   returns a logical true value, or returns that value and doesn't
;;   evaluate any of the other expressions, otherwise it returns the
;;   value of the last expression. (or) returns nil."
;;   {:added "1.0"}
;;   ([] nil)
;;   ([x] x)
;;   ([x & next]
;;       `(let [or# ~x]
;;          (if or# or# (or ~@next)))))


(source when)
;; (defmacro when
;;   "Evaluates test. If logical true, evaluates body in an implicit do."
;;   {:added "1.0"}
;;   [test & body]
;;   (list 'if test (cons 'do body)))


(source when-not)
;; (defmacro when-not
;;   "Evaluates test. If logical false, evaluates body in an implicit do."
;;   {:added "1.0"}
;;   [test & body]
;;     (list 'if test nil (cons 'do body)))


(source when-let)
;; (defmacro when-let
;;   "bindings => binding-form test
;; 
;;   When test is true, evaluates body with binding-form bound to the value of test"
;;   {:added "1.0"}
;;   [bindings & body]
;;   (assert-args
;;      (vector? bindings) "a vector for its binding"
;;      (= 2 (count bindings)) "exactly 2 forms in binding vector")
;;    (let [form (bindings 0) tst (bindings 1)]
;;     `(let [temp# ~tst]
;;        (when temp#
;;          (let [~form temp#]
;;            ~@body)))))


(source when-some)
;; (defmacro when-some
;;   "bindings => binding-form test
;; 
;;    When test is not nil, evaluates body with binding-form bound to the
;;    value of test"
;;   {:added "1.6"}
;;   [bindings & body]
;;   (assert-args
;;      (vector? bindings) "a vector for its binding"
;;      (= 2 (count bindings)) "exactly 2 forms in binding vector")
;;    (let [form (bindings 0) tst (bindings 1)]
;;     `(let [temp# ~tst]
;;        (if (nil? temp#)
;;          nil
;;          (let [~form temp#]
;;            ~@body)))))


(source when-first)
;; (defmacro when-first
;;   "bindings => x xs
;; 
;;   Roughly the same as (when (seq xs) (let [x (first xs)] body)) but xs is evaluated only once"
;;   {:added "1.0"}
;;   [bindings & body]
;;   (assert-args
;;      (vector? bindings) "a vector for its binding"
;;      (= 2 (count bindings)) "exactly 2 forms in binding vector")
;;   (let [[x xs] bindings]
;;     `(when-let [xs# (seq ~xs)]
;;        (let [~x (first xs#)]
;;            ~@body))))


(source cond)
;; (defmacro cond
;;   "Takes a set of test/expr pairs. It evaluates each test one at a
;;   time.  If a test returns logical true, cond evaluates and returns
;;   the value of the corresponding expr and doesn't evaluate any of the
;;   other tests or exprs. (cond) returns nil."
;;   {:added "1.0"}
;;   [& clauses]
;;     (when clauses
;;       (list 'if (first clauses)
;;             (if (next clauses)
;;                 (second clauses)
;;                 (throw (IllegalArgumentException.
;;                          "cond requires an even number of forms")))
;;             (cons 'clojure.core/cond (next (next clauses))))))


(source condp)
;; (defmacro condp
;;   "Takes a binary predicate, an expression, and a set of clauses.
;;   Each clause can take the form of either:
;; 
;;   Each clause can take the form of either:
;; 
;;   test-expr result-expr
;; 
;;   test-expr :>> result-fn
;; 
;;   Note :>> is an ordinary keyword.
;; 
;;   For each clause, (pred test-expr expr) is evaluated. If it returns
;;   logical true, the clause is a match. If a binary clause matches, the
;;   result-expr is returned, if a ternary clause matches, its result-fn,
;;   which must be a unary function, is called with the result of the
;;   predicate as its argument, the result of that call being the return
;;   value of condp. A single default expression can follow the clauses,
;;   and its value will be returned if no clause matches. If no default
;;   expression is provided and no clause matches, an
;;   IllegalArgumentException is thrown."
;; 
;;   {:added "1.0"}
;; 
;;   [pred expr & clauses]
;;   (let [gpred (gensym "pred__")
;;         gexpr (gensym "expr__")
;;         emit (fn emit [pred expr args]
;;                (let [[[a b c :as clause] more]
;;                        (split-at (if (= :>> (second args)) 3 2) args)
;;                        n (count clause)]
;;                  (cond
;;                   (= 0 n) `(throw (IllegalArgumentException. (str "No matching clause: " ~expr)))
;;                   (= 1 n) a
;;                   (= 2 n) `(if (~pred ~a ~expr)
;;                              ~b
;;                              ~(emit pred expr more))
;;                   :else `(if-let [p# (~pred ~a ~expr)]
;;                            (~c p#)
;;                            ~(emit pred expr more)))))]
;;     `(let [~gpred ~pred
;;            ~gexpr ~expr]
;;        ~(emit gpred gexpr clauses))))


(source conde)

(source condu)

(source conda)

(source cond->)

(source cond->>)
;; (defmacro cond->>
;;   "Takes an expression and a set of test/form pairs. Threads expr (via ->>)
;;   through each form for which the corresponding test expression
;;   is true.  Note that, unlike cond branching, cond->> threading does not short circuit
;;   after the first true test expression."
;;   {:added "1.5"}
;;   [expr & clauses]
;;   (assert (even? (count clauses)))
;;   (let [g (gensym)
;;         steps (map (fn [[test step]] `(if ~test (->> ~g ~step) ~g))
;;                    (partition 2 clauses))]
;;     `(let [~g ~expr
;;            ~@(interleave (repeat g) (butlast steps))]
;;        ~(if (empty? steps)
;;           g
;;           (last steps)))))


(source case)
;; (defmacro case
;;   "Takes an expression, and a set of clauses.
;; 
;;   Each clause can take the form of either:
;; 
;;   test-constant result-expr
;; 
;;   (test-constant1 ... test-constantN)  result-expr
;; 
;;   The test-constants are not evaluated. They must be compile-time
;;   literals, and need not be quoted.  If the expression is equal to a
;;   test-constant, the corresponding result-expr is returned. A single
;;   default expression can follow the clauses, and its value will be
;;   returned if no clause matches. If no default expression is provided
;;   and no clause matches, an IllegalArgumentException is thrown.
;;
;;   Unlike cond and condp, case does a constant-time dispatch, the
;;   clauses are not considered sequentially.  All manner of constant
;;   expressions are acceptable in case, including numbers, strings,
;;   symbols, keywords, and (Clojure) composites thereof. Note that since
;;   lists are used to group multiple constants that map to the same
;;   expression, a vector can be used to match a list if needed. The
;;   test-constants need not be all of the same type."
;;   {:added "1.2"}
;; 
;;   [e & clauses]
;;   (let [ge (with-meta (gensym) {:tag Object})
;;         default (if (odd? (count clauses))
;;                   (last clauses)
;;                   `(throw (IllegalArgumentException. (str "No matching clause: " ~ge))))]
;;     (if (> 2 (count clauses))
;;       `(let [~ge ~e] ~default)
;;       (let [pairs (partition 2 clauses)
;;             assoc-test (fn assoc-test [m test expr]
;;                          (if (contains? m test)
;;                            (throw (IllegalArgumentException. (str "Duplicate case test constant: " test)))
;;                            (assoc m test expr)))
;;             pairs (reduce1
;;                        (fn [m [test expr]]
;;                          (if (seq? test)
;;                            (reduce1 #(assoc-test %1 %2 expr) m test)
;;                            (assoc-test m test expr)))
;;                        {} pairs)
;;             tests (keys pairs)
;;             thens (vals pairs)
;;             mode (cond
;;                    (every? #(and (integer? %) (<= Integer/MIN_VALUE % Integer/MAX_VALUE)) tests)
;;                    :ints
;;                    (every? keyword? tests)
;;                    :identity
;;                    :else :hashes)]
;;         (condp = mode
;;           :ints
;;           (let [[shift mask imap switch-type] (prep-ints tests thens)]
;;             `(let [~ge ~e] (case* ~ge ~shift ~mask ~default ~imap ~switch-type :int)))
;;           :hashes
;;           (let [[shift mask imap switch-type skip-check] (prep-hashes ge default tests thens)]
;;             `(let [~ge ~e] (case* ~ge ~shift ~mask ~default ~imap ~switch-type :hash-equiv ~skip-check)))
;;           :identity
;;           (let [[shift mask imap switch-type skip-check] (prep-hashes ge default tests thens)]
;;             `(let [~ge ~e] (case* ~ge ~shift ~mask ~default ~imap ~switch-type :hash-identity ~skip-check))))))))


(defn -main
  [& args]
  (println "Finito"))
