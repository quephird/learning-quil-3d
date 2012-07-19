(ns texture-2
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def img (atom nil))

(defn setup []
  (reset! img (load-image "src/main/resources/berlin-1.jpg"))
  (no-stroke))

(defn draw []
  (background 0)
  (translate (/ screen-w 2) (/ screen-h 2))
  (rotate-y (- (* (/ (mouse-x) screen-w) PConstants/TWO_PI) PConstants/PI))
  (rotate-z (/ PConstants/PI 6))
  (begin-shape)
  (texture @img)
  (vertex -100 -100 0 0 0)
  (vertex 100 -40 0 400 120)
  (vertex 0 100 0 200 400)
  (end-shape)
  )

(defsketch main
  :title "texture-2"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
