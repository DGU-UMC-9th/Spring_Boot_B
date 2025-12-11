package com.example.umc9th.domain.review.dto;

import java.time.LocalDateTime;

public record ReviewMyReviewResponse(
        Long reviewId,
        String restaurantName,
        Integer reviewStar,
        String body,
        LocalDateTime createdAt
) {
}

