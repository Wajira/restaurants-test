package com.test.restaurants.services;

import com.test.restaurants.dto.Session;
import com.test.restaurants.dto.User;
import com.test.restaurants.services.impl.RestaurantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantServiceImpl restaurantService;


    private User testUser;
    private Session testSession;

    @Before
    public void setUp() {
        testUser = new User("John");
        testSession = new Session();
    }

    @Test
    public void testSubmitRestaurant() {
//        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(testRestaurant);

        String result = restaurantService.submitRestaurant("sessionId", "Restaurant1", testUser);

        assertEquals("Restaurant submitted successfully!", result);
    }

    @Test
    public void testSubmitRestaurantInvalidSession() {
//        when(restaurantRepository.findById(anyString())).thenReturn(java.util.Optional.empty());

        String result = restaurantService.submitRestaurant("invalidSession", "Restaurant1", testUser);

        assertEquals("Invalid session or session has ended.", result);
    }

    @Test
    public void testSubmitRestaurantSessionEnded() {
        testSession.setEnded(true);
//        when(restaurantRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        String result = restaurantService.submitRestaurant("sessionId", "Restaurant1", testUser);

        assertEquals("Invalid session or session has ended.", result);
    }

    @Test
    public void testGetRestaurantSuggestions() {
//        when(restaurantRepository.findById(anyString())).thenReturn(java.util.Optional.ofNullable(testSession));

        List<String> suggestions = restaurantService.getRestaurantSuggestions("sessionId");

    }

    @Test
    public void testGetRestaurantSuggestionsInvalidSession() {
//        when(restaurantRepository.findById(anyString())).thenReturn(java.util.Optional.empty());

        List<String> suggestions = restaurantService.getRestaurantSuggestions("invalidSession");

        assertEquals(null, suggestions);
    }

}