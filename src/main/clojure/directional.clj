(ns directional
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (no-stroke)
  (fill 204)
  )

(defn draw []
  (background 0)

  (let [dir-y (* (- (/ (mouse-y) screen-h) 0.5) 2)
        dir-x (* (- (/ (mouse-x) screen-w) 0.5) 2)]
    (directional-light 204 204 204 (- dir-x) (- dir-y) -1))

  (translate (- (/ screen-w 2) 100) (/ screen-h 2) 0)
  (sphere 80)
  (translate 200 0 0)
  (sphere 80)
  )

(defsketch main
  :title "directional"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
