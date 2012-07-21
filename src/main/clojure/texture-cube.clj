(ns texture-cube
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def vertices
  [[-1 -1 1]
  [1 -1 1]
  [1 1 1]
  [-1 1 1]
  [1 -1 -1]
  [-1 -1 -1]
  [-1 1 -1]
  [1 1 -1]])

(def img (atom nil))
(def rot-x (atom (/ PConstants/PI 4)))
(def rot-y (atom (/ PConstants/PI 4)))

(defn setup []
  (reset! img (load-image "src/main/resources/berlin-1.jpg"))
  (texture-mode :normalized)
  (fill 255)
  )

(defn- draw-textured-cube []
  (begin-shape :quads)
  (texture @img)
  (no-stroke)

  (doseq [face [[0 1 2 3]
                [4 5 6 7]
                [3 2 7 6]
                [5 4 1 0]
                [1 4 7 2]
                [5 0 3 6]]]
    (doseq [v (map #(concat %1 %2) (map #(vertices %) face) [[0 0][1 0][1 1][0 1]])]
      (apply vertex v)))

  (end-shape)
  )

(defn- mouse-dragged []
  (swap! rot-x + (* (- (pmouse-y) (mouse-y)) 0.01))
  (swap! rot-y + (* (- (mouse-x) (pmouse-x)) 0.01))
  )

(defn draw []
  (background 0)
  (translate (/ screen-w 2) (/ screen-h 2) -100)
  (rotate-x @rot-x)
  (rotate-y @rot-y)
  (scale 90)
  (draw-textured-cube)
  )

(defsketch main
  :title "texture-cube"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl
  :mouse-dragged mouse-dragged
  )
