(ns foreclojure.data-set
  (:use [somnium.congomongo]))

(defn load-problems []
  (do
    (insert! :seqs
             {:_id "problems"
              :seq 87})
    (insert! :problems
             {:_id 1
              :title "Nothing but the Truth"
              :times-solved 0
              :description "This is a haskell expression. Enter a value which will make the expression evaluate to True.  Don't over think it!  If you are confused, see the <a href='/directions'>getting started</a> page.  Hint: True is equal to True."
              :tags ["elementary"]
              :approved true
              :tests ["__ == True"]})

    (insert! :problems
             {:_id 2
              :title "Simple Math"
              :times-solved 0
              :description "The precedence of mathematical operators is the same as you expect it."
              :tags ["elementary"]
              :approved true
              :tests ["__ == 10 - 2 * 3"]})

    (insert! :problems
             {:_id 3
              :title "Intro to Lists"
              :times-solved 0
              :description "Lists can be constructed in place, either literally or using ranges."
              :tags["elementary"]
              :approved true
              :tests ["__ == [1,2,3,4,5]"
                      "__ == [1..5]"]})

    (insert! :problems
             {:_id 4
              :title "Lists: (:)"
              :times-solved 0
              :description "(:) takes an item and the list and returns a new list with an item prepended to the given list."
              :tags["elementary"]
              :approved true
              :tests ["__ == 1 : [2,3]"
                      "__ == 1 : 2 : [3]"
                      "__ == 1 : 2 : 3 : []"]})

    (insert! :problems
             {:_id 5
              :title "Intro to Functions"
              :times-solved 0
              :description "Haskell has a number of different ways to create functions."
              :tags["elementary"]
              :approved true
              :defs  "addFive :: Integer -> Integer
addFive x = x + 5
__ = -- Write your code here"
              :tests ["__ == (\\x -> 5 + x) 3"
                      "__ == (5+) 3"
                      "__ == addFive 3 -- Function addFive is defined below"
                      ]})

    (insert! :problems
           {:_id 6
            :title "Double Down"
            :times-solved 0
            :description "Write a function which doubles a number."
            :tags ["elementary"]
            :approved true
            :tests ["__ 2  == 4"
                    "__ 3  == 6"
                    "__ 11 == 22"
                    "__ 7  == 14"]})

    (insert! :problems
             {:_id 7
              :title "Hello World"
              :times-solved 0
              :description "Write a function which returns a personalized greeting."

              :tags ["elementary"]
              :approved true
              :tests ["__ \"Dave\" == \"Hello, Dave!\""
                      "__ \"Jenn\" == \"Hello, Jenn!\""
                      "__ \"Rhea\" == \"Hello, Rhea!\""]})

    (insert! :problems
             {:_id 8
              :title "Lists: map"
              :times-solved 0
              :description "The map function takes two arguments: a function (f) and a list (xs).  Map returns a new sequence consisting of the result of applying f to each element of xs."
              :tags["elementary"]
              :approved true
              :tests ["__ == map (5*) [1..3]"]})

    (insert! :problems
             {:_id 9
              :title "Sequences: filter"
              :times-solved 0
              :description "The filter function takes two arguments: a predicate function (f) and a list (xs).  Filter returns a new sequence consisting of all the elements of xs for which (f item) returns True."
              :tags["elementary"]
              :approved true
              :tests ["__ == filter (5>) [3..7]"]})

    (insert! :problems
             {:_id 10
              :title "Last Element"
              :times-solved 0
              :restricted ["last"]
              :description "Write a function which returns the last element in a sequence."
              :tags ["easy" "list" "Prelude"]
              :approved true
              :tests ["__ [1..5] == 5"
                      "__ [3,2,1] == 1"
                      "__ \"haskell\" == 'l'"]})

    (insert! :problems
             {:_id 11
              :title "Length of a list"
              :times-solved 0
              :description "Write a function which returns the amount of elements in the list."
              :tags["easy" "list" "Prelude"]
              :restricted ["length"]
              :approved true
              :tests ["__ [2..6] == 5"
                      "__ \"string is a list\" == 16"]})
      ))
