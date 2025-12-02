package com.example.umc9th2.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateReviewRequestDTO {
        private String body;
        private Float score;
    }

    @Builder
    @Getter
    public static class CreateReviewResponseDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class MyReviewListDTO {
        private List<MyReviewDto> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
