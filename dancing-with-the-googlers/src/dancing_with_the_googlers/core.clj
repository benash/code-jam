(ns dancing-with-the-googlers.core
  (require [clojure.string :as str]))

(defn read-lines [file]
  (str/split-lines (slurp file)))

(def whitespace #"\s+")

(defn best-of [n]
  (condp = n
    0 [0 false]
    1 [1 false]
    2 [1 true]
    (let [m (mod n 3)]
      (condp = m
      0 [(/ n 3) true]
      1 [(int (java.lang.Math/ceil (/ n 3))) false]
      2 [(int (java.lang.Math/ceil (/ n 3))) true]))))

(defn unsurprising [p t]
  (count (filter (fn [[x ok?]] (<= p x)) t)))

(defn surprising [p t]
  (count (filter (fn [[x ok?]] (and ok? (= (dec p) x))) t)))

(defn solve [S p t]
  (let [maxes (map best-of t)]
    (+ (unsurprising p maxes)
       (min (surprising p maxes) S))))

(defn solve-line [line]
  (let [[N S p & t]
        (map #(Integer/parseInt %)
             (str/split line whitespace))]
    (solve S p t)))

(defn run [file]
  (let [rest-lines (rest (read-lines file))]
    (map solve-line rest-lines)))

(defn write-lines [file lines]
  (spit file (str (str/join "\n" lines) "\n")))

(defn output [file solution]
  (write-lines file (map-indexed #(str "Case #" (inc %1) ": " %2) solution)))

