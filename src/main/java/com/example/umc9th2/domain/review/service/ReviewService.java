package com.example.umc9th2.domain.review.service;

import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.domain.review.converter.ReviewConverter;
import com.example.umc9th2.domain.review.dto.MyReviewDto;
import com.example.umc9th2.domain.review.dto.ReviewDTO;
import com.example.umc9th2.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.store.repository.StoreRepository;
import com.example.umc9th2.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc9th2.global.apiPayload.code.status.StoreErrorCode;
import com.example.umc9th2.global.apiPayload.handler.MemberHandler;
import com.example.umc9th2.global.apiPayload.handler.StoreHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // --- Command (데이터 변경) ---
    @Transactional
    public Review createReview(Long memberId, Long storeId, ReviewDTO.CreateReviewRequestDTO request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);

        return reviewRepository.save(review);
    }

    // --- Query (데이터 조회) ---
    @Transactional(readOnly = true)
    //이거 왜 에러나는지 모르겠음.
    public Page<MyReviewDto> getMyReviews(Long memberId, String storeName, Integer rating, Pageable pageable) {

        // (memberId 존재 여부 확인 - 선택적)
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        // Repository의 QueryDSL 메서드 호출
        return reviewRepository.findMyReviews(memberId, storeName, rating, pageable);
    }

    @Transactional
    public Review createReview(Long memberId, Long storeId, ReviewRequestDTO.JoinDTO request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);

        return reviewRepository.save(review);
    }
}
