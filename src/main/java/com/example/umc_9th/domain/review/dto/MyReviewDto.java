package com.example.umc_9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MyReviewDto {
    private String storeName;
    private Float score;
    private String body;

    // QueryDSL이 이 생성자를 호출하여 DTO를 직접 생성합니다.
    @QueryProjection
    public MyReviewDto(String storeName, Float score, String body) {
        this.storeName = storeName;
        this.score = score;
        this.body = body;
    }
}