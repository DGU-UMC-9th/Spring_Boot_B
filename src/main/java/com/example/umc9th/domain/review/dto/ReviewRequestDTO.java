package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class AddReviewDTO {
        @NotNull
        private Long userId;
        @NotNull
        @DecimalMin("0.0")
        @DecimalMax("5.0")
        private Float score;
        @NotBlank
        private String body;
    }
}

