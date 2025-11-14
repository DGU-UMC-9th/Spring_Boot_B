package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/users/{userId}/reviews")
    public ApiResponse<Page<ReviewMyReviewResponse>> getMyReviews(@PathVariable Long userId,
                                                                     @RequestParam(required = false) String restaurantName,
                                                                     @RequestParam(required = false) Integer ratingFloor,
                                                                     @PageableDefault(size = 10) Pageable pageable) {
        Page<ReviewMyReviewResponse> response = reviewService.getMyReviews(userId, restaurantName, ratingFloor, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }
}






