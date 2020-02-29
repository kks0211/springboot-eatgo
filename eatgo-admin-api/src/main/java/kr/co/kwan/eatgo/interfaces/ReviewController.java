package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.ReviewService;
import kr.co.kwan.eatgo.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {
        Review review = reviewService.addReview(restaurantId, resource);
        String url = "/restaurants/" + restaurantId + "/reviews/" + review.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @GetMapping("/reviews")
    public List<Review> list(){
        List<Review> reviews = reviewService.getReviews();
        return reviews;
    }
}
