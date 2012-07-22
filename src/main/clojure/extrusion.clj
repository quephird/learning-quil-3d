(ns extrusion
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def angle (atom 0.0))
(def image-w (atom 0))
(def image-h (atom 0))
(def brightnesses (atom []))

(defn- load-image-data []
  (let [img (load-image "src/main/resources/ystone08.jpg")]
    (.loadPixels img)
    (reset! image-w (.width img))
    (reset! image-h (.height img))
    (reset! brightnesses (into [] (map #(brightness %) (.pixels img))))
    )
  )

(defn setup []
  (load-image-data)
  )

(defn draw []
  (background 0)

  (swap! angle + 0.005)
  (translate (/ screen-w 2) 0 -128)
  (rotate-y @angle)
  (translate (- (/ @image-w 2)) 100 -128)

  (doseq [i (range (count @brightnesses))]
    (stroke (@brightnesses i))
    (point (mod i @image-w) (quot i @image-h) (- (@brightnesses i)))
    )
  )

(defsketch main
  :title "extrusion"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
