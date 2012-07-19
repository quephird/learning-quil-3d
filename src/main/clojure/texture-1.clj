(ns texture-1
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
  (vertex 100 -100 0 400 0)
  (vertex 100 100 0 400 400)
  (vertex -100 100 0 0 400)
  (end-shape)
  )

(defsketch main
  :title "texture-1"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
