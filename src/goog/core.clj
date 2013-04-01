(ns goog.core
  (:require [clojure.string :as str]))

(defn char-range
  "Returns a lazy seq of chars from start to end
  (inclusive), by step, where start defaults to Character/MIN_VALUE,
  step to 1, and end to Character/MAX_VALUE."
  ([] (char-range (Character/MIN_VALUE) (Character/MAX_VALUE) 1))
  ([end] (char-range (Character/MIN_VALUE) end 1))
  ([start end] (char-range start end 1))
  ([start end step]
     (map char (range (int start) (inc (int end)) step))))

(def goog-string "ynficwlbkuomxsevzpdrjgthaq")
(def eng-string (char-range \a \z))

(def table (zipmap (seq goog-string) (seq eng-string)))

(defn read-lines [file]
  (str/split-lines (slurp file)))

(defn goog->eng [s]
  (apply str (map #(or (get table %) %) (seq s))))

(defn output [results]
  (doseq [[result case-num]
          (map list results (range 1 (inc (count results))))]
    (println (format "Case #%d: %s" case-num result))))

(defn run [file]
  (let [[num-tests-line & test-lines] (read-lines file)
        num-tests (Integer/parseInt num-tests-line)
        tests (remove str/blank? test-lines)]
    (output (map goog->eng tests))))

