package com.test.restaurants.controllers;

import com.test.restaurants.dto.User;
import com.test.restaurants.services.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

    @Test
    public void testSubmitRestaurant() {
        when(restaurantService.submitRestaurant(anyString(), anyString(), any(User.class)))
                .thenReturn("Restaurant submitted successfully!");

        ResponseEntity<String> response = restaurantController.submitRestaurant("sessionId", "Restaurant1", new User("John"));

        assertEquals("Restaurant submitted successfully!", response.getBody());
    }

    @Test
    public void testGetRestaurantSuggestions() {
        when(restaurantService.getRestaurantSuggestions(anyString())).thenReturn(Arrays.asList("Restaurant1", "Restaurant2"));

        ResponseEntity<List<String>> response = restaurantController.getRestaurantSuggestions("sessionId");

        assertEquals(Arrays.asList("Restaurant1", "Restaurant2"), response.getBody());
    }
}
