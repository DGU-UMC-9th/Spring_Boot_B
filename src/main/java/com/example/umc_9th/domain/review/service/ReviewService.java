package com.example.umc_9th.domain.review.service;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.review.converter.ReviewConverter;
import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.dto.ReviewDTO;
import com.example.umc_9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.repository.StoreRepository;
import com.example.umc_9th.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc_9th.global.apiPayload.code.status.StoreErrorCode;
import com.example.umc_9th.global.apiPayload.handler.MemberHandler;
import com.example.umc_9th.global.apiPayload.handler.StoreHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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

    @Transactional(readOnly = true)
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10); // 페이지당 10개
        return reviewRepository.findAllByMember(member, pageRequest);
    }
}