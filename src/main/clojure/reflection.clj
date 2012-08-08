(ns reflection
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (no-stroke)
  (color-mode :rgb 1)
  (fill 0.4)
  )

(defn draw []
  (background 0)
  (translate (/ screen-w 2) (/ screen-h 2))
  (light-specular 1 1 1)
  (directional-light 0.8 0.8 0.8 0 0 -1)
  (let [s (/ (mouse-x) screen-w)]
    (specular s s s)
    (sphere 120))
  )

(defsketch main
  :title "reflection"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
