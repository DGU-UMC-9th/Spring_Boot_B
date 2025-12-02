package com.example.umc9th2.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class JoinDTO {

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String body;

        @NotNull(message = "별점은 필수입니다.")
        private Float score;
    }
}
