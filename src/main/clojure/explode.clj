(ns explode
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def cell-size 2)
(def image-pixels (atom []))
(def image-w (atom 0))
(def image-h (atom 0))
(def columns (atom 0))
(def rows (atom 0))

(defn- load-image-data []
  (let [img (load-image "src/main/resources/eames.jpg")]
    (.loadPixels img)
    (reset! image-pixels (into [] (.pixels img)))
    (reset! image-w (.width img))
    (reset! image-h (.height img))
    (reset! columns (/ @image-w cell-size))
    (reset! rows (/ @image-h cell-size))
    )
  )

(defn setup []
  (load-image-data)
  )

(defn draw []
  (background 0)
  (no-stroke)
  (doseq [i (range @columns)]
    (doseq [j (range @rows)]
      (let [x (+ (* i cell-size) (/ cell-size 2))
            y (+ (* j cell-size) (/ cell-size 2))
            loc (+ x (* y @image-w))
            c (@image-pixels loc)
            z (- (/ (* (mouse-x) (brightness (@image-pixels loc))) @image-w) 20.0)]
        (push-matrix)
        (translate (+ x 200) (+ y 100) z)
        (fill c 204)
        (rect-mode :center)
        (rect 0 0 cell-size cell-size)
        (pop-matrix)
        )
      )
    )
  )

(defsketch main
  :title "explode"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
