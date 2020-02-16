package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.RestaurantService;
import kr.co.kwan.eatgo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        //restaurants.add(new Restaurant(1004L, "Bob zip", "seoul"));
        restaurants.add(Restaurant.builder()
                        .id(1004L)
                        .name("Bob Zip")
                        .address("Seoul")
                        .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1004")))
                .andExpect(content().string(containsString("Bob Zip")));

    }

    @Test
    public void detailWithExisted() throws Exception {
        //Restaurant restaurant1 = new Restaurant(1004L, "Bob Zip", "seoul");
        Restaurant restaurant = Restaurant.builder()
                                .id(1004L)
                                .name("Bob Zip")
                                .address("Seoul")
                                .build();
        MenuItem menuItem = MenuItem.builder().name("Kimchi").build();
        restaurant.setMenuItems(Arrays.asList(menuItem));

        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("Great")
                .build();
        restaurant.setReviews(Arrays.asList(review));
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1004")))
                .andExpect(content().string(containsString("Bob Zip")))
                .andExpect(content().string(containsString("Kimchi")))
                .andExpect(content().string(containsString("Great")));
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));

    }

}