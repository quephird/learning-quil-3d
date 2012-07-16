(ns spot
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def concentration 600)

(defn setup []
  (no-stroke)
  (fill 204)
  (sphere-detail 60)
  )

(defn draw []
  (background 0)

  ; Light the bottom of the sphere
  (directional-light 51 102 126 0 -1 0)

  ; Orange light on the upper-right of the sphere
  (spot-light 204 153 0 360 160 600 0 0 -1 (/ PConstants/PI 2) 600)

  ; Moving spotlight that follows the mouse
  (spot-light 102 153 204 360 (mouse-y) 600 0 0 -1 (/ PConstants/PI 2) 600)

  (translate (/ screen-w 2) (/ screen-h 2) 0)
  (sphere 120)
  )

(defsketch main
  :title "spot"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
