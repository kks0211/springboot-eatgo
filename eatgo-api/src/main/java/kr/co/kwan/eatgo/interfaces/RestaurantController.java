package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.RestaurantService;
import kr.co.kwan.eatgo.domain.MenuItem;
import kr.co.kwan.eatgo.domain.MenuItemRepository;
import kr.co.kwan.eatgo.domain.Restaurant;
import kr.co.kwan.eatgo.domain.RestaurantRepository;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        //기본정보 + 메뉴정보
        return restaurant;
    }

}
