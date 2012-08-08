## Description

No magic here; just a set of code examples exercising the Processing API.
They are, for the most part, transliterations of the examples found here:
http://processing.org/learning/3d/primitives3d.html
In some cases, I have tried to write code that is more idiomatic to Clojure. 

## Running the examples

This project uses Leiningen, version 1.5.2 or greater, so you will need to get it in order to run this locally.
After downloading the project, run the following steps at the project root:

    lein deps
    lein repl

Once the REPL is done loading, then simply run any sketch of your choice, e.g.:

    (load-file "./src/main/clojure/primitives-3d.clj")

