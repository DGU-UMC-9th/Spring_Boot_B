package com.example.umc9th2.domain.review.controller;


import com.example.umc9th2.domain.review.dto.MyReviewDto;
import com.example.umc9th2.domain.review.dto.ReviewResDTO;
import com.example.umc9th2.domain.review.exception.ReviewSuccessCode;
import com.example.umc9th2.domain.review.service.ReviewService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // (기존 설정에 맞게 변경 가능)
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/my")
    public ResponseEntity<Page<MyReviewDto>> getMyReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Long memberId = 1L;

        Page<MyReviewDto> reviews = reviewService.getMyReviews(memberId, storeName, rating, pageable);
        return ResponseEntity.ok(reviews);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, null);
    }
}
