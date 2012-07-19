(ns texture-3
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def tube-res 32)
(def img (atom nil))
(def tube-x (atom []))
(def tube-y (atom []))

(defn- init-vectors []
  (let [angle (/ 270 tube-res)]
    (reset! tube-x (into [] (map #(Math/cos (radians (* % angle))) (range tube-res))))
    (reset! tube-y (into [] (map #(Math/sin (radians (* % angle))) (range tube-res))))
  ))

(defn setup []
  (reset! img (load-image "src/main/resources/berlin-1.jpg"))
  (init-vectors)
  (no-stroke))

(defn- draw-partial-cylinder []
  (begin-shape :quad-strip)
  (texture @img)
  (doseq [i (range tube-res)]
   (let [x (* (@tube-x i) 100)
         z (* (@tube-y i) 100)
         u (* i (/ (.width @img) tube-res))]
     (vertex x -100 z u 0)
     (vertex x 100 z u (.height @img))))
  (end-shape)
  )

(defn- draw-quad []
  (begin-shape :quads)
  (texture @img)
  (vertex 0 -100 0 0 0)
  (vertex 100 -100 0 100 0)
  (vertex 100 100 0 100 100)
  (vertex 0 100 0 0 100)
  (end-shape)
  )

(defn draw []
  (background 0)
  (translate (/ screen-w 2) (/ screen-h 2))
  (rotate-x (- (* (/ (mouse-y) screen-h) PConstants/TWO_PI) PConstants/PI))
  (rotate-y (- (* (/ (mouse-x) screen-w) PConstants/TWO_PI) PConstants/PI))
  (draw-partial-cylinder)
  (draw-quad)
  )

(defsketch main
  :title "texture-3"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
