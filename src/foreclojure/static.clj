(ns foreclojure.static
  (:use [compojure.core            :only [defroutes GET]]
        [clojure.string            :only [join]]
        [foreclojure.problems      :only [solved-stats]]
        [foreclojure.config        :only [repo-url]]
        [foreclojure.ring-utils    :only [static-url]]
        [foreclojure.template      :only [def-page]]
        [hiccup.form               :only [hidden-field]]))

(def df
  (let [df (java.text.DecimalFormat.)
        dfs (java.text.DecimalFormatSymbols.)]
    (.setGroupingSeparator dfs \,)
    (.setDecimalFormatSymbols df dfs)
    df))

(def-page welcome-page []
  {:title "4Haskell &ndash; Welcome!"
   :fork-banner true
   :content
   [:div#welcome
    [:div#totalcount
      (hidden-field :counter-value (:total @solved-stats))
      [:p
        [:span#totalcounter (.format df (:total @solved-stats))] " problems solved and counting!"]]
    [:div
     [:h3 "What is 4Haskell?"]
     [:p "4Haskell is a resource to help fledgling haskellers learn the language through interactive problems.  4Haskell is not meant to teach you, instead it aims to give some practice.  See 'Help' for more information on solving problems."]]

    [:div
     [:h3 "Is this site written in Haskell?"]
     "No.  This site is a fork of " [:a {:href "http://4clojure.com"} "4Clojure.com"] " and is written in Clojure"]]})

(defn code-sample [& code]
  (list [:br]
        [:pre (join "\n" code)]
        [:br]))

(defn solution-samples [& sols]
  (list [:br]
        (for [sol sols] [:li [:pre sol]])
        [:br]))

(def-page help-page []
  {:title "Help"
   :content
   [:div#help-content
    [:div#getting-started
     [:h2 "Getting Started"]
     [:div
      "4Haskell challenges users to solve "
      [:a {:href "http://en.wikipedia.org/wiki/K%C5%8Dan"} "koan-style"]
      " problems.  You'll be asked to finish the code so that it passes the tests. "
      "Each test is a haskell expression with a blank indicated by \"__\". To pass the test, the expression should result to True. " 
      "You can get to it in two ways. For simple problems you can just provide a substitution for the blank. Consider the first problem:"
      (code-sample "__ == True")
      "You are asked to provide something that equals to True. Any of the following would be considered correct answers:"
      (solution-samples "True"
                        "not False"
                        "null []")

      "Many problems will expect that the blank is a function.  Here is a problem which asks you to provide a function to double a number:"
      (code-sample "__ 2 == 4"
                   "__ 4 == 8"
                   "__ 8 == 16"
                   "__ 7 == 14")
      "Any of the following expressions are valid solutions:"

      (solution-samples "(2*)"
                        "(*2)"
                        "(\\x -> 2 * x)")

      "However, you might have some difficulties with more complicated problems. "
      "Consider a problem where you are asked to provide a function which returns the last element of the list:"
      (code-sample "__ [1..5] == 5"
                   "__ [3,2,1] == 1]")
      "While you can solve it with:"
      (code-sample "(\\l -> let last (x:[]) = x; last (_:xs) = last xs in last l)")
      "I wouldn't enjoy writing such code. That's why 4Haskell allows to simply define the blank using normal haskell syntax:"
      (code-sample "__ :: [a] -> a"
                   "__ (x:[]) = x"
                   "__ (x:xs) = __ xs")
      "You might want to alias \"__\" with some meaningful name"
      (code-sample "__ = last"
                   ""
                   "last :: [a] -> a"
                   "last (x:[]) = x"
                   "last (x:xs) = last xs")
      "You are free to define other functions, data types, typeclasses, instances if you need it."
      [:br][:br]
      "You should now be ready to start solving problems.  Happy coding!"]]
    ]})

(defroutes static-routes
  (GET "/directions" [] (help-page)))
