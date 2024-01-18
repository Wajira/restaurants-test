package com.test.restaurants.controllers;

import com.test.restaurants.dto.User;
import com.test.restaurants.services.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.Silent.class)
public class SessionControllerTest {

    @InjectMocks
    private SessionController sessionController;

    @Mock
    private SessionService sessionService;

    @Test
    public void testCreateSession() {
        Mockito.lenient().when(sessionService.createSession(any(User.class))).thenReturn("Successfully Created Session..!");

        ResponseEntity<String> response = sessionController.createSession(new User("John"));

        assertEquals("Successfully added into session..!", response.getBody());
    }

}
