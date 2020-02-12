package kr.co.kwan.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {
        super("Could Not Find Restaurant" + id);
    }
}
