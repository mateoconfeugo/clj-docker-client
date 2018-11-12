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

(ns clj-docker-client.core-test
  (:require [clojure.test :refer :all]
            [clj-docker-client.core :refer :all])
  (:import (java.nio.file Files)
           (java.nio.file.attribute FileAttribute)
           (java.io File)
           (com.spotify.docker.client.messages RemovedImage)))

(def sha-pattern #"\b[0-9a-f]{5,40}\b")

(defn temp-docker-dir
  []
  (let [content     "FROM busybox:musl\nRUN touch test.txt\n"
        tmp-dir     (str
                      (.toAbsolutePath
                        (Files/createTempDirectory
                          "docker-test"
                          (into-array FileAttribute []))))
        docker-file (str tmp-dir
                         File/separator
                         "Dockerfile")
        _           (spit docker-file content)]
    tmp-dir))

(deftest test-images
  (with-open [conn (connect)]
    (testing "Pulling an image by name"
      (is (nil? (pull conn "busybox:musl"))))
    (testing "Building an image from a Dockerfile"
      (let [id (build conn (temp-docker-dir))]
        (is (not (nil? (re-matches sha-pattern id))))
        (image-rm conn id)))
    (testing "Removing an image"
      (let [id (build conn (temp-docker-dir))]
        (is (instance? RemovedImage (first (image-rm conn id))))))
    (testing "Listing all images"
      (let [id (build conn (temp-docker-dir))]
        (is (not (empty? (->> (image-ls conn)
                              (filter #(= (:id %) id))))))
        (image-rm conn id)))))