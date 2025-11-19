package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.enums.ReviewRatingGroup;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.review.repository.result.ReviewSummaryProjection;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public Review addReview(Long restaurantId, ReviewRequestDTO.AddReviewDTO request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.RESTAURANT_NOT_FOUND));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, restaurant, user);

        return reviewRepository.save(review);
    }

    public Page<ReviewMyReviewResponse> getMyReviews(Long userId,
                                                     String restaurantName,
                                                     Integer ratingFloor,
                                                     Pageable pageable) {
        ReviewRatingGroup ratingGroup = ReviewRatingGroup.fromValue(ratingFloor);

        Page<ReviewSummaryProjection> projectionPage = reviewRepository.findMyReviews(
                userId,
                restaurantName,
                ratingGroup,
                pageable
        );

        return projectionPage.map(ReviewConverter::toMyReviewResponse);
    }
}








