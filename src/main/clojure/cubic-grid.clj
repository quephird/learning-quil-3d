(ns cubic-grid
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def box-size 40)
(def margin (* box-size 2))
(def depth 400)

(defn setup []
  (no-stroke))

(defn draw []
  (background 255)

  (translate (/ screen-w 2) (/ screen-h 2) (- depth))
  (rotate-y (* (frame-count) 0.01))
  (rotate-x (* (frame-count) 0.01))

  (let [is (take (inc (/ depth box-size)) (iterate #(+ % box-size) (+ (- (/ depth 2)) margin)))
        js (take (inc (/ (* screen-h 2) box-size)) (iterate #(+ % box-size) (+ (- screen-h) margin)))
        ks (take (inc (/ (* screen-w 2) box-size)) (iterate #(+ % box-size) (+ (- screen-w) margin)))]
    (doseq [i is]
      (push-matrix)
      (doseq [j js]
        (push-matrix)
        (doseq [k ks]
          (push-matrix)
          (let [box-fill (color (Math/abs i) (Math/abs j) (Math/abs k) 50)]
            (translate k j i)
            (fill box-fill)
            (box box-size box-size box-size))
          (pop-matrix))
        (pop-matrix))
      (pop-matrix)))
  )

(defsketch main
  :title "cubic grid"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
