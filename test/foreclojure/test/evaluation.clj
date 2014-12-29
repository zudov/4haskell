(ns foreclojure.test.evaluation
  (:use [clojure.test]
        [foreclojure.core]
        [foreclojure.data-set]
        [foreclojure.problems]))

;(facts "Problems"
  ;(doseq [problem (take 3 all-problems)]
    ;(facts "True is true"
      ;(mueval (first (:right (:solutions true-is-true))) [] (first (:tests true-is-true))) => nil)))
(defn test-mueval [solution tests expected]
  (doseq [test_ tests]
    (testing test_
      (is (expected (mueval solution [] test_))))))

(deftest eval-test
  (doseq [{:keys [title solutions tests]} (filter :solutions all-problems)]
    (testing title
      (testing "Correct solutions"
        (doseq [solution (:right solutions)]
          (test-mueval solution tests nil?)))
      (testing "Wrong solutions"
        (doseq [solution (:wrong solutions)]
          (test-mueval solution tests string?)))
      (testing "Won't compile solutions"
        (doseq [solution (:wont-compile solutions)]
          (test-mueval solution tests string?))))))
