package com.example.umc_9th.domain.review.controller;

import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // (기존 설정에 맞게 변경 가능)
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/my")
    public ResponseEntity<Page<MyReviewDto>> getMyReviews(
            // @AuthenticationPrincipal CustomUserDetails userDetails, // TODO: 실제로는 Spring Security로 사용자 ID를 가져와야 함
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Long memberId = 1L;

        Page<MyReviewDto> reviews = reviewService.getMyReviews(memberId, storeName, rating, pageable);
        return ResponseEntity.ok(reviews);
    }
}