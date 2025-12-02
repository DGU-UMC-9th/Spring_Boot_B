package com.example.umc_9th.domain.review.converter;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.dto.MyReviewDto;
import com.example.umc_9th.domain.review.dto.ReviewDTO;
import com.example.umc_9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Review toReview(ReviewRequestDTO.JoinDTO request, Member member, Store store) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();
    }



    public static ReviewDTO.CreateReviewResponseDTO toCreateReviewResultDTO(Review review){
        return ReviewDTO.CreateReviewResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ReviewDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> page) {
        List<MyReviewDto> myReviewDtos = page.stream()
                .map(review -> new MyReviewDto(
                        review.getStore().getName(),
                        review.getScore(),
                        review.getBody()
                ))
                .collect(Collectors.toList());

        return ReviewDTO.MyReviewListDTO.builder()
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .listSize(myReviewDtos.size())
                .reviewList(myReviewDtos)
                .build();
    }

    public static ReviewDTO.MyReviewListDTO toMyReviewListDTOFromDTO(Page<MyReviewDto> page) {
        return ReviewDTO.MyReviewListDTO.builder()
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .listSize(page.getContent().size())
                .reviewList(page.getContent()) // 이미 변환되어 있으므로 그대로 넣음
                .build();
    }

}
