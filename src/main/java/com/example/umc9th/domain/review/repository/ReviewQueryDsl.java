package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;
import java.util.function.Predicate;

public interface ReviewQueryDsl {
    List<Review> searchReview(Predicate predicate);
}
