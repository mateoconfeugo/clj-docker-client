;   This file is part of clj-docker-client.
;
;   clj-docker-client is free software: you can redistribute it and/or modify
;   it under the terms of the GNU General Public License as published by
;   the Free Software Foundation, either version 3 of the License, or
;   (at your option) any later version.
;
;   clj-docker-client is distributed in the hope that it will be useful,
;   but WITHOUT ANY WARRANTY; without even the implied warranty of
;   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;   GNU General Public License for more details.
;
;   You should have received a copy of the GNU General Public License
;   along with clj-docker-client. If not, see <http://www.gnu.org/licenses/>.

(defproject mateoconfeugo/clj-docker-client "0.1.9"
  :author "Rahul De <rahul@mailbox.org>"
  :url "https://github.com/lispyclouds/clj-docker-client"
  :description "And idiomatic clojure client for Docker."
  :license {:name "GPL 3.0"
            :url  "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [byte-streams "0.2.4"]
                 [com.spotify/docker-client "8.14.5"]
                 [ch.qos.logback/logback-classic "1.2.3"]]
  :plugins [[lein-ancient "0.6.15"]]
  :global-vars {*warn-on-reflection* true}
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.9.0"]]}})
