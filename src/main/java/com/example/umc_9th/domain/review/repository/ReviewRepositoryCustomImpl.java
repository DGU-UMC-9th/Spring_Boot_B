package com.example.umc_9th.domain.review.repository;

import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.dto.QMyReviewDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.umc_9th.domain.member.entity.QMember.member;
import static com.example.umc_9th.domain.review.entity.QReview.review;
import static com.example.umc_9th.domain.store.entity.QStore.store;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MyReviewDto> findMyReviews(Long memberId, String storeName, Integer rating, Pageable pageable) {

        // 1. 데이터 조회 (DTO 프로젝션 사용)
        List<MyReviewDto> content = queryFactory
                .select(new QMyReviewDto(
                        store.name,
                        review.score,
                        review.body
                ))
                .from(review)
                .join(review.member, member)
                .join(review.store, store)
                .where(
                        member.id.eq(memberId),   // 1. 기본 조건 (내가 쓴 리뷰)
                        storeNameEq(storeName),   // 2. 동적 조건 (가게 이름)
                        ratingEq(rating)          // 3. 동적 조건 (별점)
                )
                .orderBy(review.id.desc()) // 최신순 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. 카운트 쿼리 (페이징을 위함)
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.member, member)
                .join(review.store, store)
                .where(
                        member.id.eq(memberId),
                        storeNameEq(storeName),
                        ratingEq(rating)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    // 동적 쿼리 - 가게 이름 (storeName 파라미터가 null이 아니면 이 조건 추가)
    private BooleanExpression storeNameEq(String storeName) {
        // StringUtils.hasText()는 null, "", " "(공백)을 모두 false로 처리
        if (!StringUtils.hasText(storeName)) {
            return null;
        }
        return store.name.eq(storeName);
    }

    // 동적 쿼리 - 별점 (rating 파라미터가 null이 아니면 이 조건 추가)
    private BooleanExpression ratingEq(Integer rating) {
        if (rating == null) {
            return null;
        }

        if (rating == 5) {
            // 5점은 5.0만 해당
            return review.score.eq(5.0f);
        } else {
            // 그 외 (4, 3, 2, 1)는 "4점대", "3점대" 등을 의미
            // 예: rating=4 -> 4.0 <= score < 5.0
            return review.score.goe(rating.doubleValue())
                    .and(review.score.lt(rating.doubleValue() + 1));
        }
    }
}
