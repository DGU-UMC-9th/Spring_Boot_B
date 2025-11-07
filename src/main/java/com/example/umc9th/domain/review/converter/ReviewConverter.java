package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.repository.result.ReviewSummaryProjection;

public final class ReviewConverter {

    private ReviewConverter() {
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

