(ns clojure-service.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [clojure.data.json :as json]))

(defn response
[request]
{:status 200
 :headers {"Content-Type" "application/json"}
 :body (json/write-str {:text "This text comes from Clojure service! :D"
                        :objects [{:title "Title 1" :content "Content 1"} {:title "Title 2" :content "Content2"}]})})

(def routes
  (route/expand-routes
    #{["/hello" :get response :route-name :hello]}))

(defn create-server
  []
  (http/create-server
    {::http/routes routes
     ::http/type :jetty
     ::http/port 8081}))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (http/start (create-server)))
