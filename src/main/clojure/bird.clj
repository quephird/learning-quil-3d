(ns bird
  (:use quil.core))

(def screen-w 640)
(def screen-h 360)

(def wing-rotation (atom 0.0))
(def bird-rotation (atom 0.0))
(def translation-xy (atom 0.0))
(def translation-z (atom 0.0))

(def px (atom 0))
(def py (atom 0))
(def pz (atom 0))

(def flap-speed (atom 0.2))

(defn setup []
  (no-stroke)
  )

(defn- move-bird []
  (reset! px (* (Math/sin (radians @translation-xy)) 170))
  (reset! py (* (Math/cos (radians @translation-xy)) 300))
  (reset! pz (* (Math/sin (radians @translation-z)) 500))
  (translate (+ (/ screen-w 2) @px) (+ (/ screen-h 2) @py) (- @pz 700))
  (rotate-x (* (Math/sin (radians @bird-rotation)) 120))
  (rotate-y (* (Math/sin (radians @bird-rotation)) 50))
  (rotate-z (* (Math/sin (radians @bird-rotation)) 65))
  )

(defn- draw-bird []
  ; Body
  (fill 153)
  (box 20 100 20)

  ; Left wing
  (fill 204)
  (push-matrix)
  (rotate-y (* (Math/sin (radians @wing-rotation)) -20))
  (rect -75 -50 75 100)
  (pop-matrix)

  ; Right wing
  (push-matrix)
  (rotate-y (* (Math/sin (radians @wing-rotation)) 20))
  (rect 0 -50 75 100)
  (pop-matrix)
  )

(defn- update-mutables []
  (swap! wing-rotation + @flap-speed)
  (if (or (> @wing-rotation 3) (< @wing-rotation -3))
    (swap! flap-speed * -1))

  (swap! bird-rotation + 0.01)
  (swap! translation-xy + 2.0)
  (swap! translation-z + 0.75)
  )

(defn draw []
  (background 0)
  (lights)
  (move-bird)
  (draw-bird)
  (update-mutables)
  )

(defsketch main
  :title "bird"
  :setup setup
  :size [screen-w screen-h]
  :draw draw
  :renderer :opengl)
