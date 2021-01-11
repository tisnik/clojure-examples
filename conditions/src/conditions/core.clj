; Ukázky použití rozhodovacích konstrukcí v programovacím jazyku Clojure

(ns conditions.core)

; Poněkud netradičně je nutné používat funkce a makra ze jmenného prostoru
; "clojure.repl", zejména funkci "doc":
(use 'clojure.repl)

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

(defn -main
  [& args]
  (println "Finito"))
