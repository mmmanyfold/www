(ns mmm.views.home
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]
            [mmm.components.projects :refer [projects-component]]
            [mmm.components.talks :refer [talks-component]]))

(defn home-view []
  [:div.home-wrap
   [:div.greet.white
    [:h1 "Let's collaborate."]
    [:p "say " [:a {:href "mailto:hello@mmmanyfold.com"} "hello@mmmanyfold.com"]]]
   [projects-component]
   [:div
    [:div.values.flex-row
      [:div.text-box
       [:h2 "inspiration"]
       [:p "We’re inspired by creative people across geographic and sector boundaries."]]
      [:div [:img {:src "img/logo-plus.png"}]]
      [:div.text-box
       [:h2 "innovation"]
       [:p "We use frameworks that give us and our clients more freedom, power and flexibility."]]
      [:div [:img {:src "img/logo-equals.png"}]]
      [:div.text-box
       [:h2 "better software"]
       [:p "We strive to make user experiences distinctly more meaningful, efficient – and enjoyable."]]]]
   [talks-component]
   [:div.flex-row.footer
     [:div.definition
      [:h1 "man·y·fold"]
      [:h2 "ˈmenēˌfōld/"]
      [:i "adverb"]
      [:ol
        [:li "by many times."]
        [:li "\"the impact would be multiplied manyfold\""]]
      [:i "adjective"]
      [:ol
       [:li "involving multiplication by many times."]
       [:li "\"the manyfold increase in ideas\""]]]
     [:div.about
      [:h1 "About"]
      [:h2 "We build websites, apps, and learning experiences."]
      [:h2 "Our mission is to turn ideas into well-crafted digital products through creative collaboration."]
      [:h2 "mmmanyfold is x + y + all of our collaborators + co-conspirators."]]]])
