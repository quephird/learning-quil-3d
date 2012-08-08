(ns move-eye
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(defn setup []
  (no-stroke)
  (fill 204)
  )

(defn draw []
  (lights)
  (background 0)

  ; Change height of the camera with mouseY
  (camera 30.0 (mouse-y) 220.0 ; eyeX, eyeY, eyeZ
          0.0 0.0 0.0          ; centerX, centerY, centerZ
          0.0 1.0 0.0)         ; upX, upY, upZ

  (no-stroke)
  (box 90)
  (stroke 255)
  (line -100 0 0 100 0 0)
  (line 0 -100 0 0 100 0)
  (line 0 0 -100 0 0 100)
  )

(defsketch main
  :title "move eye"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
