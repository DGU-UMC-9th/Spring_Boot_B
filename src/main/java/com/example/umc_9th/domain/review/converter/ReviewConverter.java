package com.example.umc_9th.domain.review.converter;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.dto.ReviewDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class ReviewConverter {

    public static ReviewDTO.CreateReviewResponseDTO toCreateReviewResponseDTO(Review review) {
        return ReviewDTO.CreateReviewResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewDTO.CreateReviewRequestDTO request, Member member, Store store) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();
    }

    // 3. 내가 쓴 리뷰 조회 (GET) : Page<MyReviewDto> -> MyReviewListDTO
    public static ReviewDTO.MyReviewListDTO toMyReviewListDTO(Page<MyReviewDto> page) {
        return ReviewDTO.MyReviewListDTO.builder()
                .reviewList(page.getContent())
                .listSize(page.getContent().size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
