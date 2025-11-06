package com.example.umc_9th.domain.review.service;


import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService { // (기존에 있다면 해당 클래스에 메서드 추가)

    private final ReviewRepository reviewRepository;

    public Page<MyReviewDto> getMyReviews(Long memberId, String storeName, Integer rating, Pageable pageable) {

        // Custom Repository의 메서드 호출
        return reviewRepository.findMyReviews(memberId, storeName, rating, pageable);
    }
}