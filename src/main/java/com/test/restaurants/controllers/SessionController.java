package com.test.restaurants.controllers;

import com.test.restaurants.dto.Session;
import com.test.restaurants.dto.User;
import com.test.restaurants.services.impl.RestaurantServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
@Api(tags = "Session API")
public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    private List<Session> activeSessions = new ArrayList<>();

    @ApiOperation("Create a new session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createSession(@RequestBody @Valid User user) {
        logger.info("Creating a Session");
        Session newSession = new Session();
//        newSession.getParticipants() = new ArrayList<>();
        newSession.getParticipants().add(user);
        activeSessions.add(newSession);
        return new ResponseEntity<>("Successfully added into session..!", HttpStatus.ACCEPTED);
    }

    @ApiOperation("Join an existing session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid session or session has ended"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @PostMapping("/{sessionId}/join")
    public ResponseEntity<String> joinSession(@PathVariable String sessionId, @RequestBody @Valid User user) {
        logger.info("Joining to a Session");
        Session session = getSessionById(sessionId);
        if (session != null && !session.isEnded()) {
            session.getParticipants().add(user);
            return new ResponseEntity<>("Joined session successfully!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Session not found or has ended.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("End a session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid session or session has already ended"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @PostMapping("/{sessionId}/end")
    public ResponseEntity<String> endSession(@PathVariable String sessionId) {
        Session session = getSessionById(sessionId);
        if (session != null && !session.isEnded()) {
            session.setEnded(true);
            logger.info("Session ended successfully!");
            return new ResponseEntity<>("Session ended successfully!", HttpStatus.ACCEPTED);
        } else {
            logger.info("Session ended failed");
            return new ResponseEntity<>("Session not found or has ended.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Get participants of a session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @GetMapping("/{sessionId}/participants")
    public ResponseEntity<List<User>> getSessionParticipants(@PathVariable String sessionId) {
        logger.info("Getting participants for Session {}",sessionId);
        Session session = getSessionById(sessionId);
        return (session != null) ? new ResponseEntity<>(session.getParticipants(), HttpStatus.ACCEPTED) : new ResponseEntity("No Session Found ", HttpStatus.NOT_FOUND);
    }

    private Session getSessionById(String sessionId) {
        return activeSessions.stream()
                .filter(session -> session.getSessionId().equals(sessionId))
                .findFirst()
                .orElse(null);
    }
}