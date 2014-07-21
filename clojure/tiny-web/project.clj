(defproject tiny-web "0.1.0-SNAPSHOT"
  :java-source-paths ["src/java"]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot mbfpp.oo.tinyweb.example
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
