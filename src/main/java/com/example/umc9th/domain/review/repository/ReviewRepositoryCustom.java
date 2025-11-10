package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<MyReviewDto> findMyReviews(Long memberId, String storeName, Integer rating, Pageable pageable);
}
