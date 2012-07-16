(ns primitives-3d
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (background 0)
  (lights))

(defn draw []
  ; Cube
  (no-stroke)
  (push-matrix)
  (translate 130 (/ screen-h 2) 0)
  (rotate-y 1.25)
  (rotate-x -0.4)
  (box 100)
  (pop-matrix)

  ; Sphere
  (no-fill)
  (stroke 255)
  (push-matrix)
  (translate 500 (* screen-h 0.35) -200)
  (sphere 280)
  (pop-matrix))

(defsketch main
  :title "primitives 3d"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
