package com.test.restaurants.services;

import com.test.restaurants.dto.User;

import java.util.List;

public interface SessionService {
    String createSession(User user);

    String joinSession(String sessionId, User user);

    String endSession(String sessionId);

    List<User> getSessionParticipants(String sessionId);
}