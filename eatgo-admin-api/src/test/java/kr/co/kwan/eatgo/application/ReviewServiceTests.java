package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.Review;
import kr.co.kwan.eatgo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReviewServiceTests {

    private ReviewService reviewService;
    @Mock
    private ReviewRepository reviewRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview(){

        Review review = Review.builder().
                                name("JOKER")
                                .score(3)
                                .description("delicious")
                                .build();
        reviewService.addReview(1004L, review);

        verify(reviewRepository).save(any());
    }

    @Test
    public void getReviews() {
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder().score(3).description("Cool!").build());
        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews();
        Review review = reviews.get(0);
        assertThat(review.getDescription()).isEqualTo("Cool!");
    }

}