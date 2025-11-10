package com.example.umc9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MyReviewDto {
    private String storeName;
    private Float score;
    private String body;

    @QueryProjection
    public MyReviewDto(String storeName, Float score, String body) {
        this.storeName = storeName;
        this.score = score;
        this.body = body;
    }
}