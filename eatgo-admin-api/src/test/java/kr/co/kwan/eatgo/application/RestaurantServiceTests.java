package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.*;
import kr.co.kwan.eatgo.domain.MenuItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class RestaurantServiceTests {

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
        //restaurantRepository = new RestaurantRepositoryImpl();
       // menuItemRepository = new ();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }


    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        //menuItems.add(new MenuItem("Kimchi"));
        menuItems.add(MenuItem.builder().name("Kimchi").build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        //Restaurant restaurant = new Restaurant(1004L,"Bob zip", "seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob Zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));

    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder().name("BeRyong")
                    .score(1).description("Bad").build());

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    @Test
    public void getRestaurantWithExisted()  {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId()).isEqualTo(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName()).isEqualTo("Kimchi");

        Review review = restaurant.getReviews().get(0);
        assertThat(review.getDescription()).isEqualTo("Bad");
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
        restaurantService.getRestaurant(404L);

    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        //Restaurant restaurant = new Restaurant("BeRyong", "Busan");
        Restaurant restaurant = Restaurant.builder()
                                .name("BeRyong")
                                .address("Busan")
                                .build();

        //Restaurant saved = new Restaurant(1234L,"BeRyong", "Busan");
        /*Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .address("Busan")
                .build();*/

        Restaurant created =  restaurantService.addRestaurant(restaurant);

        assertThat(created.getId()).isEqualTo(1234L);
    }

    @Test
    public void updateRestaurant() {

        //Restaurant restaurant = new Restaurant(1004L, "Bob Zip", "Seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob Zip")
                .address("Seoul")
                .build();
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
        restaurantService.updateRestaurant(1004L, "Sool Zip", "Busan");

        assertThat(restaurant.getName()).isEqualTo("Sool Zip");
        assertThat(restaurant.getAddress()).isEqualTo("Busan");
    }
}