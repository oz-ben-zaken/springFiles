package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    public static List<Restaurant> restaurants = new ArrayList<Restaurant>();
    public static int ID = 0;

    private static int getID() {
        return ++ID;
    }

    static {
        restaurants.add(new Restaurant(getID(), "Ratatouille", new Address("jerusalem boulevards", 45, (short) 3), "first", 40.5));
        restaurants.add(new Restaurant(getID(), "Olive Garden", new Address("ah dkalim", 13, (short) 1), "first", 40.5));
        restaurants.add(new Restaurant(getID(), "Niso The Fisherman", new Address("shavit", 198, (short) 2), "first", 40.5));
        restaurants.add(new Restaurant(getID(), "BBB", new Address(), "first", 40.5));
    }

    @RequestMapping("/restaurant/{id}")
    public Restaurant getRestaurant(@PathVariable String id) {
        var _id = Integer.parseInt(id);
        for (var restaurant : restaurants)
            if (restaurant.id == _id)
                return restaurant;
        return new Restaurant();
    }

    @RequestMapping("/restaurantname/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name) {
        for (var restaurant : restaurants)
            if (restaurant.name.equals(name))
                return restaurant;
        return new Restaurant();
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.id = getID();
        restaurants.add(restaurant);
        return restaurant;
    }

    @PutMapping("/restaurant/{id}")
    public boolean updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable String id) {
        var _id = Integer.parseInt(id);
        restaurant.id = _id;
        for (var r : restaurants)
            if (r.id == _id) {
                var index = restaurants.indexOf(r);
                restaurants.remove(index);
                restaurants.add(index, restaurant);
                return true;
            }
        return false;
    }

    @DeleteMapping("/restaurant/{id}")
    public String deleteRestaurant(@PathVariable String id) {
        var _id = Integer.parseInt(id);
        for (var restaurant : restaurants)
            if (restaurant.id == _id) {
                var index = restaurants.indexOf(restaurant);
                restaurants.remove(index);
                return "done";
            }
        return "som ting went wong";
    }

    @DeleteMapping("/restaurant")
    public void deleteRestaurant() {
        restaurants.clear();
    }

}
