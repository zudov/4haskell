(ns foreclojure.test.evaluation
  (:use [clojure.test]
        [foreclojure.core]
        [foreclojure.data-set]
        [foreclojure.problems]))

;(facts "Problems"
  ;(doseq [problem (take 3 all-problems)]
    ;(facts "True is true"
      ;(mueval (first (:right (:solutions true-is-true))) [] (first (:tests true-is-true))) => nil)))
(defn test-mueval [solution tests restricted expected]
  (is (expected (mueval solution restricted tests))))

(deftest eval-test
  (doseq [{:keys [title solutions tests restricted]} (filter :solutions all-problems)]
    (testing title
      (testing "Correct solutions"
        (doseq [solution (:right solutions)]
          (test-mueval solution tests restricted (fn [resp] (every? #(= "Passed" (% "tag"))
                                                         (resp "contents"))))))
      (testing "Wrong solutions"
        (doseq [solution (:wrong solutions)]
          (test-mueval solution tests restricted (fn [resp] (every? #(= "Failed" (% "tag"))
                                                         (resp "contents"))))))
      (testing "Won't compile solutions"
        (doseq [solution (:wont-compile solutions)]
          (test-mueval solution tests restricted (fn [resp] (every? #(= "Failed" (% "tag"))
                                                         (resp "contents")))))))))
