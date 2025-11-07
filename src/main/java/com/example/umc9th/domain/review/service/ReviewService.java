package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.enums.ReviewRatingGroup;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.review.repository.result.ReviewSummaryProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewMyReviewResponse> getMyReviews(Long userId,
                                                     String restaurantName,
                                                     Integer ratingFloor,
                                                     Pageable pageable) {
        ReviewRatingGroup ratingGroup = ReviewRatingGroup.fromValue(ratingFloor);

        Page<ReviewSummaryProjection> projectionPage = reviewRepository.findMyReviews(
                userId,
                restaurantName,
                ratingGroup,
                pageable
        );

        return projectionPage.map(ReviewConverter::toMyReviewResponse);
    }
}



