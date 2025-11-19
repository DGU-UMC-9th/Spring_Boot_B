package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResponseDTO.AddResultDTO> addReview(@PathVariable Long restaurantId,
                                                                 @RequestBody @Valid ReviewRequestDTO.AddReviewDTO request) {
        Review review = reviewService.addReview(restaurantId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, ReviewConverter.toAddResultDTO(review));
    }

    @GetMapping("/users/{userId}/reviews")
    public ApiResponse<Page<ReviewMyReviewResponse>> getMyReviews(@PathVariable Long userId,
                                                                     @RequestParam(required = false) String restaurantName,
                                                                     @RequestParam(required = false) Integer ratingFloor,
                                                                     @PageableDefault(size = 10) Pageable pageable) {
        Page<ReviewMyReviewResponse> response = reviewService.getMyReviews(userId, restaurantName, ratingFloor, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }
}






