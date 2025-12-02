package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.dto.ReviewDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.service.ReviewService;
import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // (기존 설정에 맞게 변경 가능)
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/my")
    public ApiResponse<ReviewDTO.MyReviewListDTO> getMyReviews(
            // TODO: @AuthenticationPrincipal 등을 통해 실제 사용자 ID를 받아와야 함
            @RequestParam(name = "memberId", defaultValue = "1") Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MyReviewDto> reviewPage = reviewService.getMyReviews(memberId, storeName, rating, pageable);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toMyReviewListDTOFromDTO(reviewPage)        );
    }

    // 2. 리뷰 작성 API
    // RESTful하게 가게 ID를 PathVariable로 받습니다.
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewDTO.CreateReviewResponseDTO> createReview(
            // TODO: @AuthenticationPrincipal 등을 통해 실제 사용자 ID를 받아와야 함
            @RequestParam(name = "memberId", defaultValue = "1") Long memberId,
            @PathVariable Long storeId,
            @RequestBody ReviewDTO.CreateReviewRequestDTO request) {

        Review review = reviewService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toCreateReviewResponseDTO(review)
        );
    }

    @GetMapping("/members/my-reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회합니다. 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 ID (토큰 대체 예정)"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<ReviewDTO.MyReviewListDTO> getMyReviewList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page") Integer page
    ) {
        Page<Review> reviewPage = reviewService.getMyReviewList(memberId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, ReviewConverter.toMyReviewListDTO(reviewPage)); // 기존 Converter 재사용
    }
}