package com.test.restaurants.services.impl;

import com.test.restaurants.dto.Session;
import com.test.restaurants.dto.User;
import com.test.restaurants.services.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.springframework.validation.ValidationException;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Override
    public String submitRestaurant(String sessionId, String restaurant, User user) {
        try {
            Session session = getSessionById(sessionId);

            if (session != null && !session.isEnded()) {
                session.getRestaurantSuggestions().add(restaurant);
                logger.info("Restaurant submitted: {} by user: {} in session: {}", restaurant, user.getUsername(), sessionId);
                return "Restaurant submitted successfully!";
            } else {
                logger.warn("Invalid session or session has ended for submission by user: {} in session: {}", user.getUsername(), sessionId);
                return "Invalid session or session has ended.";
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return "An unexpected error occurred.";
        }
    }

    @Override
    public List<String> getRestaurantSuggestions(String sessionId) {
        Session session = getSessionById(sessionId);
        return (session != null) ? session.getRestaurantSuggestions() : null;
    }

    private Session getSessionById(String sessionId) {
        // Implement this method to retrieve a session by its ID (e.g., from a repository)
        return null;
    }
}