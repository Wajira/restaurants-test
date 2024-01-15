package com.test.restaurants.services;

import com.test.restaurants.dto.Session;
import com.test.restaurants.dto.User;
import com.test.restaurants.services.impl.SessionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

//    @Mock
//    private SessionRepository sessionRepository;

    private User testUser;
    private Session testSession;

    @Before
    public void setUp() {
        testUser = new User("John");
        testSession = new Session();
    }

    @Test
    public void testCreateSession() {
//        when(sessionRepository.save(any(Session.class))).thenReturn(testSession);

        String sessionId = sessionService.createSession(testUser);

        assertEquals(testSession.getSessionId(), sessionId);
    }

    @Test
    public void testJoinSession() {
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        String result = sessionService.joinSession("sessionId", testUser);

        assertEquals("Joined session successfully!", result);
    }

    @Test
    public void testJoinSessionInvalidSession() {
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.empty());

        String result = sessionService.joinSession("invalidSession", testUser);

        assertEquals("Session not found or has ended.", result);
    }

    @Test
    public void testJoinSessionSessionEnded() {
        testSession.setEnded(true);
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        String result = sessionService.joinSession("sessionId", testUser);

        assertEquals("Session not found or has ended.", result);
    }

    @Test
    public void testEndSession() {
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        String result = sessionService.endSession("sessionId");

        assertEquals("Session ended successfully!", result);
    }

    @Test
    public void testEndSessionInvalidSession() {
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.empty());

        String result = sessionService.endSession("invalidSession");

        assertEquals("Session not found or has already ended.", result);
    }

    @Test
    public void testEndSessionSessionEnded() {
        testSession.setEnded(true);
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        String result = sessionService.endSession("sessionId");

        assertEquals("Session not found or has already ended.", result);
    }

    @Test
    public void testGetSessionParticipants() {
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        List<User> participants = sessionService.getSessionParticipants("sessionId");

        // Assuming the result should contain participant information
        // Add assertions accordingly
    }

    @Test
    public void testGetSessionParticipantsInvalidSession() {
//        when(sessionRepository.findById(anyString())).thenReturn(java.util.Optional.empty());

        List<User> participants = sessionService.getSessionParticipants("invalidSession");

        assertEquals(null, participants);
    }

    // Add similar tests for other scenarios as needed
}