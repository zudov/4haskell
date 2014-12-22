(ns foreclojure.data-set
  (:use [somnium.congomongo]))

; Problems defenitions
(def true-is-true
  {:title "Nothing but the Truth"
   :description "This is a haskell expression. Enter a value which will make the expression evaluate to True.  Don't over think it!  If you are confused, see the <a href='/directions'>getting started</a> page.  Hint: True is equal to True."
   :tags ["elementary"]
   :tests ["__ == True"]})

(def simple-math
  {:title "Simple Math"
   :description "The precedence of mathematical operators is the same as you expect it."
   :tags ["elementary"]
   :tests ["__ == 10 - 2 * 3"]})

(def lists-intro
  {:title "Intro to Lists"
   :description "Lists can be constructed in place, either literally or using ranges."
   :tags["elementary" "lists"]
   :tests ["__ == [1,2,3,4,5]"
           "__ == [1..5]"]})

(def lists-cons
  {:title "Lists: (:)"
   :description "(:) takes an item and the list and returns a new list with an item prepended to the given list."
   :tags["elementary" "lists"]
   :tests ["__ == 1 : [2,3]"
           "__ == 1 : 2 : [3]"
           "__ == 1 : 2 : 3 : []"]})

(def functions-intro
  {:title "Intro to Functions"
   :description "Haskell has a number of different ways to create functions."
   :tags["elementary"]
   :defs  "addFive :: Integer -> Integer
addFive x = x + 5
__ = -- Write your code here"
   :tests ["__ == (\\x -> 5 + x) 3"
           "__ == (5+) 3"
           "__ == addFive 3 -- Function addFive is defined below"
           ]})

(def functions-double
  {:title "Double Down"
   :description "Write a function which doubles a number."
   :tags ["elementary"]
   :tests ["__ 2  == 4"
           "__ 3  == 6"
           "__ 11 == 22"
           "__ 7  == 14"]})
(def odd-number
  {:title "Odd number"
   :description "Write a function which tests if the number is odd."
   :tags ["lists" "recursion"]
   :restricted ["odd" "even" "mod" "div"]
   :tests ["__ 2 == False"
           "__ 3 == True"
           "__ 10 == False"]})

(def hello-world
  {:title "Hello World"
   :description "Write a function which returns a personalized greeting."

   :tags ["elementary" "strings"]
   :tests ["__ \"Dave\" == \"Hello, Dave!\""
           "__ \"Jenn\" == \"Hello, Jenn!\""
           "__ \"Rhea\" == \"Hello, Rhea!\""]})

(def lists-map
  {:title "Lists: map"
   :description "The map function takes two arguments: a function (f) and a list (xs).  Map returns a new sequence consisting of the result of applying f to each element of xs."
   :tags["elementary" "lists"]
   :tests ["__ == map (5*) [1..3]"]})

(def lists-filter
  {:title "Lists: filter"
   :description "The filter function takes two arguments: a predicate function (f) and a list (xs).  Filter returns a new sequence consisting of all the elements of xs for which (f item) returns True."
   :tags ["elementary" "lists"]
   :tests ["__ == filter (5>) [3..7]"]})

(def length-of-a-list
    {:title "Length of a list"
     :description "Write a function which returns the amount of elements in the list."
     :tags ["easy" "list" "Prelude"]
     :restricted ["length" "lists"]
     :tests [ "__ [2..6] == 5"
              "__ \"string is a list\" == 16"]})

(def last-element
  {:title "Last Element"
   :restricted ["last" "lists"]
   :description "Write a function which returns the last element in a sequence."
   :tags ["easy" "list" "Prelude"]
   :tests ["__ [1..5] == 5"
           "__ [3,2,1] == 1"
           "__ \"haskell\" == 'l'"]})

(defn add-boilerplate-fields [n problem]
  (assoc problem :_id n
                 :times-solved 0
                 :approved true))

; Here the problems can be arranged in specific order. New problems can easily
; be added.
(def all-problems
  (map add-boilerplate-fields
       (iterate inc 1) 
       [; Introduction
        true-is-true
        simple-math
        ; Functions
        functions-intro
        functions-double
        odd-number
        ; Strings
        hello-world
        ; Lists
        lists-intro
        lists-cons
        lists-map
        lists-filter
        last-element
        length-of-a-list
       ]))

; Finally we load them
(defn load-problems []
  (do (insert! :seqs
              {:_id "problems"
               :seq (count all-problems)})
      (last (map #(insert! :problems %) all-problems))))
