package com.test.restaurants.controllers;

import com.test.restaurants.dto.User;
import com.test.restaurants.services.RestaurantService;
import com.test.restaurants.services.impl.RestaurantServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/restaurant")
@Api(tags = "Restaurant API")
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation("Submit a restaurant suggestion for a session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid session or session has ended"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @PostMapping("/{sessionId}/submit")
    public ResponseEntity<String> submitRestaurant(
            @PathVariable String sessionId,
            @RequestBody @Validated String restaurant,
            @RequestAttribute("user") User user) {
        try {
            logger.info("Submit a Restaurants for Session {}", sessionId);
            String result = restaurantService.submitRestaurant(sessionId, restaurant, user);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error Submit a Restaurants for Session {}", sessionId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @ApiOperation("Get restaurant suggestions for a session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @GetMapping("/{sessionId}/suggestions")
    public ResponseEntity<List<String>> getRestaurantSuggestions(@PathVariable String sessionId) {
        try {
            logger.info("Get Restaurant suggestions for Session {}", sessionId);
            List<String> suggestions = restaurantService.getRestaurantSuggestions(sessionId);
            Random randomizer = new Random();
            String random = suggestions.get(randomizer.nextInt(suggestions.size()));
            if (suggestions != null) {
                return ResponseEntity.ok(suggestions);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error while getting Restaurant suggestions for Session {}", sessionId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation("Get selected restaurant for a session")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 500, message = "An unexpected error occurred")
    })
    @GetMapping("/{sessionId}/restaurant")
    public ResponseEntity<String> getSelectedRestaurant(@PathVariable String sessionId) {
        try {
            logger.info("Get Selected Restaurant for Session {}", sessionId);
            List<String> suggestions = restaurantService.getRestaurantSuggestions(sessionId);
            Random randomizer = new Random();
            String restaurant = suggestions.get(randomizer.nextInt(suggestions.size()));
            if (suggestions != null) {
                return ResponseEntity.ok(restaurant);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error while getting Restaurant for Session {}", sessionId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}