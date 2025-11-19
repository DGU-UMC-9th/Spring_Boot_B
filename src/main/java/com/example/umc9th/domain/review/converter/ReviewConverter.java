package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.repository.result.ReviewSummaryProjection;

import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.user.entity.User;

import java.time.LocalDateTime;

public final class ReviewConverter {

    private ReviewConverter() {
    }

    public static ReviewResponseDTO.AddResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.AddReviewDTO request, Restaurant restaurant, User user) {
        return Review.builder()
                .restaurant(restaurant)
                .user(user)
                .reviewStar(Math.round(request.getScore())) // Float -> Integer 변환
                .body(request.getBody())
                .build();
    }

    public static ReviewMyReviewResponse toMyReviewResponse(ReviewSummaryProjection projection) {
        return new ReviewMyReviewResponse(
                projection.reviewId(),
                projection.restaurantName(),
                projection.reviewStar(),
                projection.body(),
                projection.createdAt()
        );
    }
}

