package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.MyReviewDto;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService { // (기존에 있다면 해당 클래스에 메서드 추가)

    private final ReviewRepository reviewRepository;

    public Page<MyReviewDto> getMyReviews(Long memberId, String storeName, Integer rating, Pageable pageable) {

        return reviewRepository.findMyReviews(memberId, storeName, rating, pageable);
        //리뷰레포지토리에 있는데 왜 에러가 나는거지?
    }
}
