package com.test.restaurants.services;

import com.test.restaurants.dto.User;

import java.util.List;

public interface RestaurantService {
    String submitRestaurant(String sessionId, String restaurant, User user);

    List<String> getRestaurantSuggestions(String sessionId);
}