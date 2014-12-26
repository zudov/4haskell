(ns foreclojure.template
  (:require [noir.session              :as   session])
  (:use     [hiccup.core               :only [html]]
            [hiccup.page               :only [doctype include-js include-css]]
            [hiccup.element            :only [javascript-tag link-to]]
            [foreclojure.config        :only [config repo-url]]
            [foreclojure.utils         :only [page-attributes rendering-info login-url approver? can-submit? codemirror-themes get-theme]]
            [foreclojure.ring-utils    :only [static-url]]))



;; Global wrapping template
(defn html-doc [body]
  (let [attrs (rendering-info (page-attributes body))
        user (session/get :user)]
    (html
     (doctype :html5)
     [:html
      [:head
       [:title (:title attrs)]
       [:link {:rel "alternate" :type "application/atom+xml" :title "Atom" :href "/problems/rss"}]
       [:link {:rel "shortcut icon" :href (static-url "favicon2.ico")}]
       (include-css "/css/demo_table.css"
                    "/css/codemirror.css"
                    "/css/style.css")
       (apply include-css (map #(format "/css/theme/%s.css" %) codemirror-themes))
       (include-js "/vendor/script/jquery-1.5.2.min.js"
                   "/vendor/script/jquery.dataTables.min.js"
                   "/vendor/script/jquery.flipCounter.1.1.pack.js"
                   "/vendor/script/jquery.easing.1.3.js"
                   "/vendor/script/jquery.dataTables.fnSetFilteringDelay.js"
                   "/script/codebox.js"
                   "/script/foreclojure.js"
                   "/vendor/script/codemirror.js"
                   "/vendor/script/codemirror-haskell.js"
                   "/vendor/script/detectmobilebrowser.js")
       (javascript-tag (format "CodeBox.theme = '%s';" (get-theme)))]
      [:body
       (when (:fork-banner attrs)
         [:div#github-banner [:a {:href repo-url
                                  :alt "Fork 4Haskell on Github!"}]])
       [:div#top
        (link-to "/" [:img#logo {:src (static-url "images/4hs-logo.png")
                                 :alt "4haskell.com"}])]
       [:div#content
        [:div#menu
         (for [[link text & [tabbed]]
               [["/" "Main Page"]
                ["/problems" "Problem List"]
                ["/users" "Top Users"]
                ["/directions" "Help"]
                ["http://tryhaskell.org" "REPL" true]
                ["https://downloads.haskell.org/~ghc/7.6-latest/docs/html/libraries/base-4.6.0.1/Prelude.html" "Docs" true]]]
           [:a.menu (assoc (when tabbed {:target "_blank"})
                      :href link)
            text])
         [:div#user-info
          (if user
            [:div
             [:span#username (str "Logged in as " user)]
             [:a#logout {:href "/logout"} "Logout"]]
            [:div
             [:a#login {:href (login-url)} "Login"]
             [:a#register {:href "/register"} "Register"]])]]
        (when user
          [:div#lower-menu
           [:span
            (link-to "/settings" "Account Settings")]
           (when (:golfing-active config)
             [:span ; deserves its own page, but just make it discoverable for now
              (link-to "/league" "Leagues")])
           (when (approver? user)
             [:span
              (link-to "/problems/unapproved" "View Unapproved Problems")])
           (when (can-submit? user)
             [:span (link-to "/problems/submit" "Submit a Problem")])])
        [:div#content_body (:content attrs)]
        [:div#footer
         "The content on 4haskell.com is available under the EPL v 1.0 license."
         (let [email "team@4haskell.com"]
           [:span
            [:a#contact {:href (str "mailto:" email)} "Contact us"]
            (str " (" email ")")])]
        (javascript-tag
         "var _gaq = _gaq || [];
          _gaq.push(['_setAccount', 'UA-22844856-1']);
          _gaq.push(['_trackPageview']);

          (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
          })();"
         )]]])))

;; Content templates
(defn content-page [{:keys [heading heading-note sub-heading main]}]
  (let [flash-message (session/flash-get :message)
        flash-error   (session/flash-get :error)]
    (list
     (when heading       [:div#heading      heading])
     (when heading-note  [:div#heading-note heading-note])
     (when sub-heading   [:div#sub-heading  sub-heading])
     (when flash-message [:div.message
                          [:span#flash-text flash-message]])
     (when flash-error   [:div.message
                          [:span#error-text flash-error]])
     (when main          [:div#main         main]))))

(defmacro def-page [page-name [& args] & code]
  `(defn ~page-name [~@args]
     (html-doc (do ~@code))))
