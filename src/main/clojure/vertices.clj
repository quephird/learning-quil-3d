(ns vertices
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup [])

(defn- draw-cap [y-coord radius angle-increment sides]
  (begin-shape :triangle-fan)
  (vertex 0 y-coord 0)
  (doseq [angle (map #(* % angle-increment) (range (inc sides)))]
    (vertex (* radius (Math/cos angle)) y-coord (* radius (Math/sin angle))))
  (end-shape))

(defn- draw-cylinder [top-radius bottom-radius tall sides]
  (let [angle-increment (/ PConstants/TWO_PI sides)]
    (begin-shape :quad-strip)
    (doseq [angle (map #(* % angle-increment) (range (inc sides)))]
      (vertex (* top-radius (Math/cos angle)) 0 (* top-radius (Math/sin angle)))
      (vertex (* bottom-radius (Math/cos angle)) tall (* bottom-radius (Math/sin angle))))
    (end-shape)

  ; If it is not a cone, draw the circular top cap
    (if (not= top-radius 0)
      (draw-cap 0 top-radius angle-increment sides))

    ; If it is not a cone, draw the circular bottom cap
    (if (not= bottom-radius 0)
      (draw-cap tall bottom-radius angle-increment sides))
    ))

(defn draw []
  (background 0)
  (lights)
  (translate (/ screen-w 2) (/ screen-h 2))
  (rotate-y (/ (* (mouse-x) PConstants/PI) screen-w))
  (rotate-z (/ (* (mouse-y) (* -1 PConstants/PI)) screen-h))
  (no-stroke)
  (fill 255 255 255)
  (translate 0 -40 0)
  (draw-cylinder 10 180 200 16)
;  (draw-cylinder 70 70 120 64)
;  (draw-cylinder 0 180 200 4)
)

(defsketch main
  :title "vertices"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
