(ns perspective
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (no-stroke)
  )

(defn draw []
  (lights)
  (background 204)

  (let [camera-y (/ screen-h 2.0)
        fov (/ (* (mouse-x) PConstants/HALF_PI) screen-w)
        camera-z (/ camera-y (Math/tan (/ fov 2.0)))
        aspect (/ screen-w screen-h)]
    (if (mouse-state)
      (perspective fov (/ aspect 2.0) (/ camera-z 10.0) (* camera-z 10.0))
      (perspective fov aspect (/ camera-z 10.0) (* camera-z 10.0)))
    )

  (translate (+ (/ screen-w 2) 30) (/ screen-h 2) 0)
  (rotate-x (- (/ PConstants/PI 6)))
  (rotate-y (+ (/ PConstants/PI 3) (/ (* (mouse-y) PConstants/PI) screen-h)))
  (box 45)
  (translate 0 0 -50)
  (box 30)
  )

(defsketch main
  :title "perspective"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
