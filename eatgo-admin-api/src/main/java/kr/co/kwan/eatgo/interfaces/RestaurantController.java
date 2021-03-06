package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.RestaurantService;
import kr.co.kwan.eatgo.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
        //Restaurant restaurant = new Restaurant(name, address);
        Restaurant restaurant = restaurantService.addRestaurant(Restaurant.builder()
                                                    .name(resource.getName())
                                                    .address(resource.getAddress())
                                                    .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id, @Valid @RequestBody Restaurant resource) {
        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }

}
