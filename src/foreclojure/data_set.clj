(ns foreclojure.data-set
  (:use [somnium.congomongo]))

; Problems defenitions
(def true-is-true
  {:title "Nothing but the Truth"
   :description "This is a haskell expression. Enter a value which will make the expression evaluate to True.  Don't over think it!  If you are confused, see the <a href='/directions'>getting started</a> page.  Hint: True is equal to True."
   :tags []
   :difficulty "elementary"
   :tests ["__ == True"]
   :solutions
     {:right ["True" "__ = True"]
      :wrong ["False"]
      :wont-compile ["true" "1"]}
   })

(def simple-math
  {:title "Simple Math"
   :description "The precedence of mathematical operators is the same as you expect it."
   :tags []
   :difficulty "elementary"
   :tests ["__ == 10 - 2 * 3"]})

(def lists-intro
  {:title "Intro to Lists"
   :description "Lists can be constructed in place, either literally or using ranges."
   :tags["lists"]
   :difficulty "elementary"
   :tests ["__ == [1,2,3,4,5]"
           "__ == [1..5]"]})

(def lists-cons
  {:title "Lists: (:)"
   :description "(:) takes an item and the list and returns a new list with an item prepended to the given list."
   :tags["lists"]
   :difficulty "elementary"
   :tests ["__ == 1 : [2,3]"
           "__ == 1 : 2 : [3]"
           "__ == 1 : 2 : 3 : []"]})

(def functions-intro
  {:title "Intro to Functions"
   :description "Haskell has a number of different ways to create functions."
   :tags []
   :difficulty "elementary"
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
   :tags []
   :difficulty "elementary"
   :tests ["__ 2  == 4"
           "__ 3  == 6"
           "__ 11 == 22"
           "__ 7  == 14"]
   :solutions
     {:right ["(*2)" "__ = (*2)" "(\\x -> x * 2)"]
      :wrong ["(*3)"]
      :wont-compile ["**3"]
     }
  })
(def odd-number
  {:title "Odd number"
   :description "Write a function which tests if the number is odd."
   :tags ["lists" "recursion"]
   :difficulty "easy"
   :restricted ["odd" "even" "mod" "div"]
   :tests ["__ 2 == False"
           "__ 3 == True"
           "__ 10 == False"]})

(def hello-world
  {:title "Hello World"
   :description "Write a function which returns a personalized greeting."
   :tags ["strings"]
   :difficulty "elementary"
   :tests ["__ \"Dave\" == \"Hello, Dave!\""
           "__ \"Jenn\" == \"Hello, Jenn!\""
           "__ \"Rhea\" == \"Hello, Rhea!\""]})

(def lists-map
  {:title "Lists: map"
   :description "The map function takes two arguments: a function (f) and a list (xs).  Map returns a new sequence consisting of the result of applying f to each element of xs."
   :tags ["lists"]
   :difficulty "elementary"
   :tests ["__ == map (5*) [1..3]"]})

(def lists-filter
  {:title "Lists: filter"
   :description "The filter function takes two arguments: a predicate function (f) and a list (xs).  Filter returns a new sequence consisting of all the elements of xs for which (f item) returns True."
   :tags ["lists"]
   :difficulty "elementary"
   :tests ["__ == filter (5>) [3..7]"]})

(def length-of-a-list
    {:title "Length of a list"
     :description "Write a function which returns the amount of elements in the list."
     :tags ["list" "Prelude"]
     :difficulty "easy" 
     :restricted ["length" "lists"]
     :tests [ "__ [2..6] == 5"
              "__ \"string is a list\" == 16"]})

(def last-element
  {:title "Last Element"
   :restricted ["last" "lists"]
   :description "Write a function which returns the last element in a sequence."
   :tags ["list" "Prelude"]
   :difficulty "easy" 
   :tests ["__ [1..5] == 5"
           "__ [3,2,1] == 1"
           "__ \"haskell\" == 'l'"]})
(def null
  {:title "Null"
   :restricted ["null" "length"]
   :description "Check if the list is empty. If I was you I would use pattern matching."
   :tags ["list" "Prelude" "pattern-match"]
   :difficulty "easy" 
   :tests ["__ []   == True"
           "__ [1]  == False"
           "__ [[]] == False -- List with an empty list is not empty itself"]})

(def take_
  {:title "Take'em"
   :restricted ["take" "splitAt"]
   :description "Take first n elements from the list. If I was you I would use pattern mathing and recursion."
   :tags ["list" "Prelude" "pattern-match" "recursion"]
   :difficulty "easy" 
   :tests ["__ 4 \"hello\" == \"hell\""
           "__ 0 [1..] == []"
           "__ 3 [1..] == [1,2,3]"]})

(def drop_
  {:title "Drop'em"
   :restricted ["drop" "splitAt"]
   :description "Drop n first elements from the list and return the rest of it. I would use pattern matching and recursion."
   :tags ["list" "Prelude" "pattern-match" "recursion"]
   :difficulty "easy" 
   :tests ["__ 3 [1..5] == [4,5]"
           "__ 1 \"4haskell\" == \"haskell\""]})

(def sum
  {:title  "Sum'em all"
   :restricted ["sum"]
   :description "Find the sum of numbers in the list. This is a perfect use-case for folding abstraction, it would allow you to solve many similar problems using the same pattern. If you aren't bold with fold take a look <a href=http://learnyouahaskell.com/higher-order-functions#folds>here</a>."
   :tags ["list" "Prelude" "fold" "higher-order"]
   :difficulty "easy" 
   :tests ["__ [1..3] == 6"
           "__ [1..5] == 15"
           "__ [] == 0"]})

(def product
  {:title  "What's you product?"
   :restricted ["product"]
   :description "Find the product of numbers in the list. This is a perfect use-case for folding abstraction, it would allow you to solve many similar problems using the same pattern. If you aren't bold with fold take a look <a href=http://learnyouahaskell.com/higher-order-functions#folds>here</a>."
   :tags ["list" "Prelude" "fold" "higher-order"]
   :difficulty "easy" 
   :tests ["__ [1..3] == 6"
           "__ [1..5] == 120"
           "__ [] == 1 -- If you are confused read about 'empty product'"]})

(def elem
  {:title "Are you there?"
   :restricted ["elem" "any"]
   :description "Check if the element is present in the list. I would use pattern match, guards and recursion."
   :tags ["list" "Prelude" "recursion" "pattern-match"]
   :difficulty "easy" 
   :tests ["__ 4 [1,2,3] == False"
           "__ 3 [1,2,3] == True"
           "__ 5 [1..] == True"]})

(def coordinate-grid
  {:title "Coordinate grid comprehension"
   :description "Define a function which takes integers m and n and returns a list of all coordinate pairs on an [0..m] × [0..n] rectangular grid. I would use list comprehension"
   :tags ["list-comprehension"]
   :difficulty "easy" 
   :tests ["__ 1 1 == [(0,0), (0,1), (1,0), (1,1)]"
           "__ 1 2 == [(0,0), (0,1), (0,2), (1,0), (1,1), (1,2)]"]})

(def functor-class "class Functor f where
    fmap :: (a -> b) -> f a -> f b\n\n")

(def functor-list
  {:title "Functor []"
   :description "Implement a functor instance for []"
   :defs (str functor-class "instance Functor [] where
    fmap = undefined -- feel free to simply use map")
   :restricted ["Functor(..)"]
   :difficulty "easy"
   :tags ["typeclasses", "functor", "list"]
   :tests ["fmap (+1) [1..5] == [2..6]"
           "fmap id [1..5] == [1..5] -- First (identity) functor law"
           "fmap ((+1) . (+2)) [1..5] == (fmap (+1) . fmap (+2)) [1..5] -- Second (composition) functor law"]
   :solutions {:right [(str functor-class "instance Functor [] where\n    fmap = map")]}
   })

(def functor-maybe
  {:title "Functor Maybe"
   :description "Implement a functor instance for Maybe"
   :defs "instance Functor Maybe where\n    fmap = undefined"
   :restricted ["Functor(..)"]
   :difficulty "easy"
   :tags ["typeclasses", "functor", "maybe"]
   :tests ["fmap (+1) (Just 0) == Just 1"
           "fmap (+1) Nothing  == Nothing"]
   :solutions
     {:right [(str functor-class "instance Functor Maybe where\n    fmap f (Just x) = Just $ f x\n    fmap f Nothing = Nothing")]}
   })

(def functor-tree
  {:title "Functor Tree"
   :description "Implement a Functor instance for the type Tree, defined below"
   :defs 
"data Tree a = Leaf a
            | Node [Tree a]
            deriving (Eq, Show)

instance Functor ITree where
    fmap = undefined"
   :difficulty "intermediate"
   :tags ["typeclasses" "functor" "maybe"]
   :tests ["fmap (^2) (Node [Leaf 1, Leaf 2, Leaf 3]) == Node [Leaf 1, Leaf 4, Leaf 9]"]
   :solutions {:right ["data Tree a = Leaf a | Node [Tree a] deriving (Eq, Show)
instance Functor Tree where
    fmap f (Leaf x) = Leaf (f x)
    fmap f (Node xs) = Node $ fmap (fmap f) xs"]}
   })

(defn add-boilerplate-fields [n problem]
  (-> problem
      (dissoc :solutions)
      (assoc :_id n
             :times-solved 0
             :approved true)))

; Here the problems can be arranged in specific order. New problems can easily
; be added.
(def all-problems
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
   coordinate-grid
   ; Prelude
   null
   length-of-a-list
   last-element
   take_
   drop_
   sum
   product
   elem
   ; Typeclassopedia
   ;; Functor
   functor-list
   functor-maybe
   functor-tree
  ])

(def all-problems-to-insert
  (map add-boilerplate-fields (iterate inc 1) all-problems))

; Finally we load them
(defn load-problems []
  (do (insert! :seqs
              {:_id "problems"
               :seq (count all-problems-to-insert)})
      (last (map #(insert! :problems %) all-problems-to-insert))))
