(ns foreclojure.data-set
  (:use [somnium.congomongo]))

;; Short cuts for linking to learning materials
(defn rwh [page section] ;; Real Word Haskell
  (str "http://book.realworldhaskell.org/read/" page ".html#" section))

(defn lyah ;; Learn you a haskell
  ([page] (str "http://learnyouahaskell.com/" page))
  ([page section] (str "http://learnyouahaskell.com/" page "#" section)))

(defn prelude [section] ;; Hackage docs for Prelude
  (str "https://downloads.haskell.org/~ghc/7.6-latest/docs/html/libraries/base-4.6.0.1/Prelude.html"
       "#" section))

(defn typeclassopedia [section]
  (str "https://www.haskell.org/haskellwiki/Typeclassopedia#" section))

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
   :links {"Getting Started" "/directions"}
   })

(def simple-math
  {:title "Simple Math"
   :description "The precedence of mathematical operators is the same as you expect it."
   :tags []
   :difficulty "elementary"
   :tests ["__ == 10 - 2 * 3"]
   :links {"RWH: Simple arithmetic" (rwh "getting-started" "starting.calc.arithmetic")}})

(def lists-intro
  {:title "An intro to lists"
   :description "Lists can be constructed in place, either literally or using ranges."
   :tags["lists"]
   :difficulty "elementary"
   :tests ["__ == [1,2,3,4,5]"
           "__ == [1..5]"]
   :links {"LYAH: An intro to lists" (lyah "starting-out" "an-intro-to-lists")
           "LYAH: Texas Ranges" (lyah "starting-out" "texas-ranges")
           "Hackage: Prelude" (prelude "g:11")}})

(def lists-cons
  {:title "Lists: (:)"
   :description "(:) takes an item and the list and returns a new list with an item prepended to the given list."
   :tags["lists"]
   :difficulty "elementary"
   :tests ["__ == 1 : [2,3]"
           "__ == 1 : 2 : [3]"
           "__ == 1 : 2 : 3 : []"]
   :links {"LYAH: An intro to lists" (lyah "starting-out" "an-intro-to-lists")
           "Hackage: Prelude" (prelude "g:11")}})

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
           ]
   :links {"LYAH: Baby's first functions" (lyah "starting-out" "babys-first-functions")
           "LYAH: Lambdas" (lyah "higher-order-functions" "lambdas")}})

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
   :links {"LYAH: Baby's first functions" (lyah "starting-out" "babys-first-functions")}
  })
(def odd-number
  {:title "Odd number"
   :description "Write a function which tests if the number is odd. I would use recursion and pattern matching."
   :tags ["lists" "recursion"]
   :difficulty "easy"
   :restricted ["odd" "even" "mod" "div"]
   :tests ["__ 2 == False"
           "__ 3 == True"
           "__ 10 == False"]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")
           "LYAH: Recursion" (lyah "recursion")}})

(def hello-world
  {:title "Hello World"
   :description "Write a function which returns a personalized greeting. Remember, strings are lists."
   :tags ["strings"]
   :difficulty "elementary"
   :tests ["__ \"Dave\" == \"Hello, Dave!\""
           "__ \"Jenn\" == \"Hello, Jenn!\""
           "__ \"Rhea\" == \"Hello, Rhea!\""]
   :links {"LYAH: An intro to lists" (lyah "starting-out" "an-intro-to-lists")
           "Hackage: Prelude" (prelude "g:11")}})

(def lists-map
  {:title "Lists: map"
   :description "The map function takes two arguments: a function f and a list xs.  Map returns a new list consisting of the result of applying f to each element of xs."
   :tags ["lists"]
   :difficulty "elementary"
   :tests ["__ == map (5*) [1..3]"]
   :links {"LYAH: An intro to lists" (lyah "starting-out" "an-intro-to-lists")
           "Hackage: Prelude" (prelude "g:11")}})

(def lists-filter
  {:title "Lists: filter"
   :description "The filter function takes two arguments: a predicate function (f) and a list (xs).  Filter returns a new sequence consisting of all the elements of xs for which (f item) returns True."
   :tags ["lists"]
   :difficulty "elementary"
   :tests ["__ == filter (5>) [3..7]"]
   :links {"LYAH: An intro to lists" (lyah "starting-out" "an-intro-to-lists")
           "Hackage: Prelude" (prelude "g:11")}})

(def length-of-a-list
    {:title "Length of a list"
     :description "Write a function which returns the amount of elements in the list. If you speak folds, use it, another good (but less expressive) method is pattern match with recursion"
     :tags ["list" "Prelude"]
     :difficulty "easy" 
     :restricted ["length" "lists"]
     :tests [ "__ [2..6] == 5"
              "__ \"string is a list\" == 16"]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")
           "LYAH: Recursion" (lyah "recursion")
           "LYAH: Only folds and horses" (lyah "higher-order-functions" "folds")}})

(def last-element
  {:title "Last Element"
   :restricted ["last" "lists"]
   :description "Write a function which returns the last element in a sequence."
   :tags ["list" "Prelude"]
   :difficulty "easy" 
   :tests ["__ [1..5] == 5"
           "__ [3,2,1] == 1"
           "__ \"haskell\" == 'l'"]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")
           "LYAH: Recursion" (lyah "recursion")}})
(def null
  {:title "Null"
   :restricted ["null" "length"]
   :description "Check if the list is empty. If I was you I would use pattern matching."
   :tags ["list" "Prelude" "pattern-match"]
   :difficulty "easy" 
   :tests ["__ []   == True"
           "__ [1]  == False"
           "__ [[]] == False -- List with an empty list is not empty itself"]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")}})

(def take_
  {:title "Take'em"
   :restricted ["take" "splitAt"]
   :description "Take first n elements from the list. If I was you I would use pattern matching and recursion."
   :tags ["list" "Prelude" "pattern-match" "recursion"]
   :difficulty "easy" 
   :tests ["__ 4 \"hello\" == \"hell\""
           "__ 0 [1..] == []"
           "__ 3 [1..] == [1,2,3]"]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")
           "LYAH: Recursion" (lyah "recursion")}})

(def drop_
  {:title "Drop'em"
   :restricted ["drop" "splitAt"]
   :description "Drop n first elements from the list and return the rest of it. I would use pattern matching and recursion."
   :tags ["list" "Prelude" "pattern-match" "recursion"]
   :difficulty "easy" 
   :tests ["__ 3 [1..5] == [4,5]"
           "__ 1 \"4haskell\" == \"haskell\""]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")
           "LYAH: Recursion" (lyah "recursion")}})

(def sum
  {:title  "Sum'em all"
   :restricted ["sum"]
   :description "Find the sum of numbers in the list. This is a perfect use-case for folding abstraction, it would allow you to solve many similar problems using the same pattern."
   :tags ["list" "Prelude" "fold" "higher-order"]
   :difficulty "easy" 
   :tests ["__ [1..3] == 6"
           "__ [1..5] == 15"
           "__ [] == 0"]
   :links {"LYAH: Only folds and horses" (lyah "higher-order-functions" "folds")}})

(def product
  {:title  "What's you product?"
   :restricted ["product"]
   :description "Find the product of numbers in the list. This is a perfect use-case for folding abstraction, it would allow you to solve many similar problems using the same pattern."
   :tags ["list" "Prelude" "fold" "higher-order"]
   :difficulty "easy" 
   :tests ["__ [1..3] == 6"
           "__ [1..5] == 120"
           "__ [] == 1 -- If you are confused read about 'empty product'"]
   :links {"LYAH: Only folds and horses" (lyah "higher-order-functions" "folds")}})

(def elem
  {:title "Are you there?"
   :restricted ["elem" "any"]
   :description "Check if the element is present in the list. I would use pattern match, guards and recursion."
   :tags ["list" "Prelude" "recursion" "pattern-match"]
   :difficulty "easy" 
   :tests ["__ 4 [1,2,3] == False"
           "__ 3 [1,2,3] == True"
           "__ 5 [1..] == True"]
   :links {"LYAH: Pattern matching" (lyah "syntax-in-functions" "pattern-matching")
           "LYAH: Recursion" (lyah "recursion")
           "LYAH: Guards, guards!" (lyah "syntax-in-functions" "guards-guards")}})

(def coordinate-grid
  {:title "Coordinate grid comprehension"
   :description "Define a function which takes integers m and n and returns a list of all coordinate pairs on an [0..m] × [0..n] rectangular grid. I would use list comprehension"
   :tags ["lists" "list-comprehension"]
   :difficulty "easy" 
   :tests ["__ 1 1 == [(0,0), (0,1), (1,0), (1,1)]"
           "__ 1 2 == [(0,0), (0,1), (0,2), (1,0), (1,1), (1,2)]"]
   :links {"LYAH: I'm a list comprehension" (lyah "starting-out" "im-a-list-comprehension")}})

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
   :links {"LYAH: The Functor typeclass" (lyah "making-our-own-types-and-typeclasses" "the-functor-typeclass")
           "Typeclassopedia: Functor" (typeclassopedia "Functor")}})

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
   :links {"LYAH: The Functor typeclass" (lyah "making-our-own-types-and-typeclasses" "the-functor-typeclass")
           "Typeclassopedia: Functor" (typeclassopedia "Functor")}})

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
   :links {"LYAH: The Functor typeclass" (lyah "making-our-own-types-and-typeclasses" "the-functor-typeclass")
           "Typeclassopedia: Functor" (typeclassopedia "Functor")}})

(defn add-boilerplate-fields [n problem]
  (-> problem
      (dissoc :solutions)
      (assoc :_id n
             :times-solved 0
             :approved true)
      (update-in [:tags] sort)))

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
   ; Lists
   lists-intro
   lists-cons
   lists-map
   lists-filter
   coordinate-grid
   ; Strings
   hello-world
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
