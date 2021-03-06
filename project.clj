(defproject mmm "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.0"]
                 [re-frame "0.9.3"]
                 [re-frisk "0.3.2"]
                 [org.clojure/core.async "0.2.391"]
                 [re-com "1.0.0"]
                 [secretary "1.2.3"]
                 [cljsjs/showdown "1.4.2-0"]
                 [cljsjs/jquery "2.2.4-0"]
                 [cljsjs/photoswipe "4.1.1-0"]
                 [compojure "1.6.0"]
                 [ring "1.6.0"]
                 [cljs-ajax "0.6.0"]
                 [environ "1.1.0"]
                 [day8.re-frame/http-fx "0.1.3"]
                 [adzerk/env "0.4.0"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-less "1.7.5"]
            [lein-s3-static-deploy "0.1.0"]]

  :aws {:access-key       ~(System/getenv "AWS_ACCESS_KEY_ID")
        :secret-key       ~(System/getenv "AWS_SECRET_ACCESS_KEY")
        :s3-static-deploy {:bucket     "www.mmmanyfold.com"
                           :local-root "resources/public"}}

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"]

  :figwheel {:css-dirs     ["resources/public/css"]
             :ring-handler mmm-clj.handler/dev-handler}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]]

    :plugins      [[lein-figwheel "0.5.8"]
                   [environ "1.0.1"]
                   [lein-doo "0.1.7"]]}}


  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/clj" "src/cljs"]
     :figwheel     {:on-jsload "mmm.core/mount-root"}
     :compiler     {:main                 mmm.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}


    {:id           "min"
     :source-paths ["src/clj" "src/cljs"]
     :jar          true
     :compiler     {:main            mmm.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/clj" "src/cljs" "test/cljs"]
     :compiler     {:main          mmm.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]}

  :main mmm-clj.server

  :aot [mmm-clj.server]

  :prep-tasks [["cljsbuild" "once" "min"] "compile"])
