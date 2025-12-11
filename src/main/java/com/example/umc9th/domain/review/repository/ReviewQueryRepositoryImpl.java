package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.restaurant.entity.QRestaurant;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.enums.ReviewRatingGroup;
import com.example.umc9th.domain.review.repository.result.ReviewSummaryProjection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    private static final QReview review = QReview.review;
    private static final QRestaurant restaurant = QRestaurant.restaurant;

    @Override
    public Page<ReviewSummaryProjection> findMyReviews(Long userId,
                                                       String restaurantName,
                                                       ReviewRatingGroup ratingGroup,
                                                       Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder()
                .and(review.user.id.eq(userId));

        if (StringUtils.hasText(restaurantName)) {
            builder.and(restaurant.name.eq(restaurantName));
        }

        BooleanExpression ratingCondition = ratingGroupCondition(ratingGroup);
        if (ratingCondition != null) {
            builder.and(ratingCondition);
        }

        List<ReviewSummaryProjection> content = queryFactory
                .select(Projections.constructor(ReviewSummaryProjection.class,
                        review.id,
                        restaurant.name,
                        review.reviewStar,
                        review.body,
                        review.createdAt
                ))
                .from(review)
                .join(review.restaurant, restaurant)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.restaurant, restaurant)
                .where(builder)
                .fetchOne();

        long totalElements = total != null ? total : 0L;
        return new PageImpl<>(content, pageable, totalElements);
    }

    private BooleanExpression ratingGroupCondition(ReviewRatingGroup ratingGroup) {
        if (ratingGroup == null) {
            return null;
        }
        return review.reviewStar.between(ratingGroup.getMinInclusive(), ratingGroup.getMaxInclusive());
    }
}

