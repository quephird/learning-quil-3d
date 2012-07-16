(defproject learning-quil-3d "0.0.1"
  :description "Learning quil 3D"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [quil "1.4.0"]
                 [net.java.dev.jogl/jogl "1.1.1a"]]
  :native-dependencies [[net.java.dev.jogl/jogl-windows-i586 "2.4.2"]]
  :source-path "src/main/clojure/"
  :jvm-opts ["-Xmx1g" "-server"])

