package kr.co.kwan.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    List<Restaurant> findAllByAddressContaining(String region);

    Optional<Restaurant> findById(Long id); //Optional 은 null 을 체크안함

    Restaurant save(Restaurant restaurant);

    List<Restaurant> findAllByAddressContainingAndCategoryId(String region, long categoryId);
}
