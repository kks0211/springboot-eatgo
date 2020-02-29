package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.Review;
import kr.co.kwan.eatgo.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Long restaurantId,Review review) {
        review.setRestaurantId(restaurantId);

        return reviewRepository.save(review);
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
