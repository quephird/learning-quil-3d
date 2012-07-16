(ns rgb-cube
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def xmag 0.0)
(def ymag 0.0)

(def colors-and-vertices
  [[[0 1 1] [-1 1 1]]
  [[1 1 1] [1 1 1]]
  [[1 0 1] [1 -1 1]]
  [[0 0 1] [-1 -1 1]]
  [[1 1 1] [1 1 1]]
  [[1 1 0] [1 1 -1]]
  [[1 0 0] [1 -1 -1]]
  [[1 0 1] [1 -1 1]]
  [[1 1 0] [1 1 -1]]
  [[0 1 0] [-1 1 -1]]
  [[0 0 0] [-1 -1 -1]]
  [[1 0 0] [1 -1 -1]]
  [[0 1 0] [-1 1 -1]]
  [[0 1 1] [-1 1 1]]
  [[0 0 1] [-1 -1 1]]
  [[0 0 0] [-1 -1 -1]]
  [[0 1 0] [-1 1 -1]]
  [[1 1 0] [1 1 -1]]
  [[1 1 1] [1 1 1]]
  [[0 1 1] [-1 1 1]]
  [[0 0 0] [-1 -1 -1]]
  [[1 0 0] [1 -1 -1]]
  [[1 0 1] [1 -1 1]]
  [[0 0 1] [-1 -1 1]]
  ])

(defn setup []
  (no-stroke)
  (color-mode :rgb 1))

(defn draw []
  (background 0.5)
  (push-matrix)
  (translate (/ screen-w 2) (/ screen-h 2) -30)

  (let [new-xmag (* (/ (mouse-x) screen-w) PConstants/TWO_PI)
        new-ymag (* (/ (mouse-y) screen-h) PConstants/TWO_PI)
        xmag-diff (- xmag new-xmag)
        ymag-diff (- ymag new-ymag)]
    (if (> (Math/abs xmag-diff) 0.01)
      (rotate-y (- xmag (/ xmag-diff 2.0)))
      (rotate-y (- xmag)))
    (if (> (Math/abs ymag-diff) 0.01)
      (rotate-x (- ymag (/ ymag-diff 2.0)))
      (rotate-x (- ymag))))

  (scale 90)
  (begin-shape :quads)
  (doseq [[c v] colors-and-vertices]
    (apply fill c)
    (apply vertex v))
  (end-shape)
  (pop-matrix)
  )

(defsketch main
  :title "rgb cube"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
