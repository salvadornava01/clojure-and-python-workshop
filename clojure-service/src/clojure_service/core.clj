(ns clojure-service.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defn response
[request]
{:status 200
 :headers {"Content-Type" "text/html"}
 :body "<h1>Hello world! :D</h1>"})

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
