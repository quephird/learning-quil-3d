(ns brick-tower
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def tower-radius 175.0)
(def bricks-per-layer 16)
(def brick-layers 18)
(def brick-color [182 62 29])

(def brick-w 60)
(def brick-h 25)
(def brick-d 25)
(def brick-vertices
  [[(/ (- brick-w) 2) (/ (- brick-h) 2) (/ brick-d 2)]
  [(/ brick-w 2) (/ (- brick-h) 2) (/ brick-d 2)]
  [(/ brick-w 2) (/ brick-h 2) (/ brick-d 2)]
  [(/ (- brick-w) 2) (/ brick-h 2) (/ brick-d 2)]
  [(/ (- brick-w) 2) (/ (- brick-h) 2) (/ (- brick-d) 2)]
  [(/ brick-w 2) (/ (- brick-h) 2) (/ (- brick-d) 2)]
  [(/ brick-w 2) (/ brick-h 2) (/ (- brick-d) 2)]
  [(/ (- brick-w) 2) (/ brick-h 2) (/ (- brick-d) 2)]]
  )


(defn setup [])

(defn- draw-brick []
  (begin-shape :quads)
  (doseq [brick-quad [[0 1 2 3] ; Front
                      [0 4 7 3] ; Left
                      [1 5 6 2] ; Right
                      [4 5 6 7] ; Back
                      [0 4 5 1] ; Top
                      [3 7 6 2] ; Bottom
                      ]]
    (doseq [vertex-num brick-quad]
      (vertex ((brick-vertices vertex-num) 0) ((brick-vertices vertex-num) 1) ((brick-vertices vertex-num) 2))))
  (end-shape)
  )

(defn draw []
  (background 0)
  (apply fill brick-color)
  (no-stroke)
  (lights)
  (translate (/ screen-w 2) (* screen-h 1.2) -380)
  ; Tip tower to see inside
  (rotate-x (radians -45))
  ; Slowly rotate tower
  (rotate-y (* (frame-count) (/ PConstants/PI 600)))

  (doseq [i (range brick-layers)]
    ; This is to alternate brick seams
    (let [init-angle (* i (/ 360 bricks-per-layer 2))]
      (doseq [j (range bricks-per-layer)]
        (let [angle (+ init-angle (* (/ 360 bricks-per-layer) j))]
          (push-matrix)
          (translate (* (Math/sin (radians angle)) tower-radius)
                     (* i (- brick-h))
                     (* (Math/cos (radians angle)) tower-radius))
          (rotate-y (radians angle))

          ; To support crenelation
          (if (not (and (= i (dec brick-layers)) (even? j)))
            (draw-brick))
          (pop-matrix)))))
  )

(defsketch main
  :title "brick tower"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
