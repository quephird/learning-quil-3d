(ns zoom
  (:import [processing.core PConstants])
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def angle (atom 0.0))
(def scale-value (atom 1.0))
(def nmx (atom 0.0))
(def nmy (atom 0.0))

(def resolution (atom 5))
(def image-w (atom 0))
(def image-h (atom 0))
(def image-pixels (atom []))

(defn- load-image-data []
  (let [img (load-image "src/main/resources/ystone08.jpg")]
    (.loadPixels img)
    (reset! image-w (.width img))
    (reset! image-h (.height img))
    (reset! image-pixels (into [] (.pixels img)))
    )
  )

(defn setup []
  (load-image-data)
  (no-fill)
  (stroke 255)
  )

(defn- mouse-moved []
  (swap! nmx + (/ (- (mouse-x) @nmx) 20))
  (swap! nmy + (/ (- (mouse-y) @nmy) 20))
  )

(defn- key-pressed []
  (if (contains? #{\1 \2 \3 \4 \5} (raw-key))
    (reset! resolution (- (int (raw-key)) 48)))
  )

(defn draw []
  (background 0)

  (if (mouse-state)
    (swap! scale-value + 0.005)
    (swap! scale-value - 0.005)
    )
  (swap! scale-value constrain-float 1.0 2.5)

  (translate (+ (/ screen-w 2) (* @nmx @scale-value) -100)
             (+ (/ screen-h 2) (* @nmy @scale-value) -200)
             -50)
  (scale @scale-value)
  (rotate-z (+ (- (/ PConstants/PI 9) @scale-value) 1.0))
  (rotate-x (- (/ (/ PConstants/PI @scale-value) 8) 0.125))
  (rotate-y (- (/ @scale-value 8) 0.125))
  (translate (- (/ screen-w 2)) (- (/ screen-h 2)) 0)

  (doseq [i (range 0 (count @image-pixels) @resolution)]
    (let [pixel-value (@image-pixels i)
          x (mod i @image-w)
          y (quot i @image-h)
          r (red pixel-value)
          g (green pixel-value)
          b (blue pixel-value)
          t (+ r g b)]
      (stroke r g b)
      (line x y (- (/ t 10) 20) x y (/ t 10))
      )
    )
  )

(defsketch main
  :title "zoom"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl
  :mouse-moved mouse-moved
  :key-pressed key-pressed
  )
