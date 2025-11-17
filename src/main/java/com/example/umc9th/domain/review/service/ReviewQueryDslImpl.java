package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewQueryDsl;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.List;
import com.querydsl.core.types.Predicate;


@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    //검색 API
    @Override
    public List<Review> searchReview(Predicate predicate) {

        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
        //select(쿼리) from(review) 식으로 바꿔야할 듯.
    }
}
