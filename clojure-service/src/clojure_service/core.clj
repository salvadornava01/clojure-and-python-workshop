(ns clojure-service.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [clojure.data.json :as json]))

(defn response
[request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str {:title "This is a simple list of some planets of the Solar System :D"
                          :articles [{:title "The Earth" 
                                      :content "The Earth is the 3rd plannet in the Solar System and the place we all live!" 
                                      :image "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/The_Blue_Marble_%28remastered%29.jpg/440px-The_Blue_Marble_%28remastered%29.jpg"} 
                                     {:title "Mars"
                                      :content "Mars is the 4th planet in the Solar System, and the place we'll live in the future!" 
                                      :image "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Mars_Valles_Marineris.jpeg/440px-Mars_Valles_Marineris.jpeg"}
                                     {:title "Jupiter" 
                                      :content "Jupiter is the most massive planet in the Solar System!" 
                                      :image "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Jupiter_New_Horizons.jpg/440px-Jupiter_New_Horizons.jpg"}]})})

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
