(ns foreclojure.users
  (:use [foreclojure.utils   :only [from-mongo get-user def-page row-class]]
        [foreclojure.config  :only [config repo-url]]
        [somnium.congomongo  :only [fetch-one fetch]]
        [compojure.core      :only [defroutes GET]]
        [hiccup.page-helpers :only [link-to]]))

(def golfer-tags (into [:contributor]
                       (when (:golfing-active config)
                         [:golfer])))

(defn get-user-id [name]
  (:_id
   (fetch-one :users
              :where {:user name}
              :only [:_id])))

(def sort-by-solved-and-date (juxt (comp count :solved) :last-login))

(defn users-sort [users]
  (reverse (sort-by sort-by-solved-and-date users)))

(defn get-users []
  (let [users (from-mongo
               (fetch :users
                      :only [:user :solved :contributor]))
        sortfn  (comp count :solved)]
    (reverse (sort-by sortfn users))))

(defn golfer? [user]
  (some user golfer-tags))

(defn email-address [username]
  (:email (fetch-one :users :where {:user username})))

(defn mailto [username]
  (link-to (str "mailto:" (email-address username))
           username))

(def-page users-page []
  [:div
   [:span.contributor "*"] " "
   (link-to repo-url "4clojure contributor")]
  [:br]
  [:table#user-table.my-table
   [:thead
    [:tr
     [:th {:style "width: 40px;"} "Rank"]
     [:th "Username"]
     [:th "Problems Solved"]]]
   (map-indexed #(vec [:tr (row-class %1)
                       [:td (inc %1)]
                       [:td
                        (when (:contributor %2)
                          [:span.contributor "* "])
                        [:a#user-profile-link {:href (str "/user/" (:user %2))} (:user %2)]]
                       [:td {:class "centered"} (count (:solved %2))]])
                (get-users))])

;; TODO: this is snagged from problems.clj but can't be imported do to cyclic dependancy, must refactor this out.
(defn get-problems
  ([]
     (from-mongo
      (fetch :problems
             :only  [:_id :difficulty]
             :where {:approved true}
             :sort  {:_id 1})))
  ([difficulty]
     (get (group-by :difficulty (get-problems)) difficulty [{}])))

(defn get-solved
  ([username]
     (:solved (get-user username)))
  ([username difficulty]
     (let [problem-groups   (group-by :difficulty (get-problems))
           difficulty-group (flatten (vector (:_id (apply merge-with vector (get problem-groups difficulty [{}])))))]
       (filter (set (get-solved username)) difficulty-group))))

(def-page user-profile [username]
    [:h3 "User: " username]
    [:hr]
    [:table
     [:tr [:td.count-label "Elementary"] [:td.count-value       (count (get-solved username "Elementary")) "/" (count (get-problems "Elementary"))]]
     [:tr [:td.count-label "Easy"      ] [:td.count-value       (count (get-solved username "Easy"))       "/" (count (get-problems "Easy"))]]
     [:tr [:td.count-label "Medium"    ] [:td.count-value       (count (get-solved username "Medium"))     "/" (count (get-problems "Medium"))]]
     [:tr [:td.count-label "Hard"      ] [:td.count-value       (count (get-solved username "Hard"))       "/" (count (get-problems "Hard"))]]
     [:tr [:td.count-total "TOTAL:"    ] [:td.count-total-value (count (get-solved username))              "/" (count (get-problems))]]])

(defroutes users-routes
  (GET "/users" [] (users-page))
  (GET "/user/:username" [username] (user-profile username)))
