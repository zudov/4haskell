# 4Haskell

An interactive problem website for learning Haskell 'by doing':
[http://www.4haskell.com](http://www.4haskell.com). It's not ready yet but hopefully will be soon.

The project is a fork of [4Clojure](https://github.com/4clojure/4clojure).

## Contributing

Anyone interested in contributing should check out
the [Issues](https://github.com/4clojure/4clojure/issues) page for ideas
on what to work on.

## Setup instructions for running locally

* Download and install [leiningen](https://github.com/technomancy/leiningen).
* Download and install [mongodb](http://www.mongodb.org/).
* Use cabal to build [mueval](https://hackage.haskell.org/package/mueval)
  * make sure that mueval binaries are in your PATH
  * test that mueval works by running `mueval -t 10 -e "2+2"`

* cd to the 4clojure project directory and run `lein deps`.
* Start up your mongodb, if you don't have autostart:

        mongod
* For the first time use, you will need to load the problem data. Run the script `load-data.sh`:

        ./load-data.sh

* Run `lein ring server`

* To run the tests: `lein test`

## Contributors

* [David Byrne](https://github.com/dbyrne) (dbyrne)
* [Alan Malloy](https://github.com/amalloy) (amalloy)
* [Anthony Grimes](https://github.com/Raynes) (raynes)
* [Carin Meier](https://github.com/gigasquid) (cmeier)
* [Clint Harrison](https://github.com/Clinteger) (clinteger)
* [Darren Austin](https://github.com/darrenaustin) (darren)
* [David Davis](https://github.com/daviddavis) (daviddavis)
* [Devin Walters](https://github.com/devn) (devn)
* [Michael Kohl](https://github.com/citizen428) (citizen428)
* [Martin Sander](https://github.com/marvinthepa) (0x89)
* [Alex McNamara](https://github.com/amcnamara) (amcnamara)
* [Ara Jeknavorian](https://github.com/arajek) (arajek)


Problem sources and inspirations:

 * [Learn you a haskell](http://learnyouahaskell.com)
 * [FP101x](https://courses.edx.org/courses/DelftX/FP101x/3T2014/info)
 * [Typeclassopedia](https://www.haskell.org/haskellwiki/Typeclassopedia#Functor)
 * Aaron Bedra's [Clojure Koans](https://github.com/functional-koans/clojure-koans)
 * [Ninety-Nine Lisp Problems](http://www.ic.unicamp.br/~meidanis/courses/mc336/2006s2/funcional/L-99_Ninety-Nine_Lisp_Problems.html)
 * [Project Euler](http://www.projecteuler.net)

## License

The source code for 4clojure is available under the Eclipse Public License v 1.0.  For more information, see LICENSE.html.
