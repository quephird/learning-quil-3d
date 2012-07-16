(ns lights-2
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (no-stroke)
  )

(defn draw []
  (background 0)
  (translate (/ screen-w 2) (/ screen-h 2))

  ; Orange point light on the right
  (point-light 150 100 0 200 -150 0)

  ; Blue directional light from the left
  (directional-light 0 102 255 1 0 0)

  ; Yellow spotlight from the front
  (spot-light 255 255 109 0 40 200 0 -0.5 -0.5 (/ PConstants/PI 2) 2)

  (rotate-y (/ (* (mouse-x) PConstants/PI) screen-w))
  (rotate-x (/ (* (mouse-y) PConstants/PI) screen-h))
  (box 150)
  )

(defsketch main
  :title "lights 2"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
