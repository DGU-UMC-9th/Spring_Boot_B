package com.example.umc9th.domain.review.repository.result;

import java.time.LocalDateTime;

public record ReviewSummaryProjection(
        Long reviewId,
        String restaurantName,
        Integer reviewStar,
        String body,
        LocalDateTime createdAt
) {
}






