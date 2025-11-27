package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.enums.ReviewRatingGroup;
import com.example.umc9th.domain.review.repository.result.ReviewSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {

    Page<ReviewSummaryProjection> findMyReviews(Long userId,
                                                String restaurantName,
                                                ReviewRatingGroup ratingGroup,
                                                Pageable pageable);
}




