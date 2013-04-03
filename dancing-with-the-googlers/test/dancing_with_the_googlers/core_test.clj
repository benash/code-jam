(ns dancing-with-the-googlers.core-test
  (:use clojure.test
        dancing-with-the-googlers.core))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= '(3 2 1 3) (run "resources/A.in")))))

(deftest b-small
  (output "resources/B-small-practice.out" (run "resources/B-small-practice.in")))

(deftest b-large
  (output "resources/B-large-practice.out" (run "resources/B-large-practice.in")))

