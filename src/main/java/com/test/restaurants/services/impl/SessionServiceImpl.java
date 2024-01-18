package com.test.restaurants.services.impl;

import com.test.restaurants.dto.Session;
import com.test.restaurants.dto.User;
import com.test.restaurants.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    private List<Session> activeSessions = new ArrayList<>();

    @Override
    public String createSession(User user) {
        logger.info("Creating new Session");
        Session newSession = new Session();
        newSession.getParticipants().add(user);
        activeSessions.add(newSession);
        return "Successfully Created Session..!";
    }

    @Override
    public String joinSession(String sessionId, User user) {
        logger.info("Joining to a Session {}",sessionId);
        Session session = getSessionById(sessionId);
        if (session != null && !session.isEnded()) {
            session.getParticipants().add(user);
            return "Joined session successfully!";
        } else {
            return "Session not found or has ended.";
        }
    }

    @Override
    public String endSession(String sessionId) {
        logger.info("Ending Session {}",sessionId);
        Session session = getSessionById(sessionId);
        if (session != null && !session.isEnded()) {
            session.setEnded(true);
            return "Session ended successfully!";
        } else {
            return "Session not found or has already ended.";
        }
    }

    @Override
    public List<User> getSessionParticipants(String sessionId) {
        Session session = getSessionById(sessionId);
        return (session != null) ? session.getParticipants() : null;
    }

    private Session getSessionById(String sessionId) {
        return activeSessions.stream()
                .filter(session -> session.getSessionId().equals(sessionId))
                .findFirst()
                .orElse(null);
    }
}