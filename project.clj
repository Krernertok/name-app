(defproject name-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "1.0.0"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [metosin/reitit "0.5.11"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [org.xerial/sqlite-jdbc "3.34.0"]]
  :main ^:skip-aot name-app.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
