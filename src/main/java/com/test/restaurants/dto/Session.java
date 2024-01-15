package com.test.restaurants.dto;

import java.util.List;

public class Session {
    private String sessionId;
    private List<User> participants;
    private List<String> restaurantSuggestions;
    private boolean ended;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<String> getRestaurantSuggestions() {
        return restaurantSuggestions;
    }

    public void setRestaurantSuggestions(List<String> restaurantSuggestions) {
        this.restaurantSuggestions = restaurantSuggestions;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    // constructors, getters, setters
}