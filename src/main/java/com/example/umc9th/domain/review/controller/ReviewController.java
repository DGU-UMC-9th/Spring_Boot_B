package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewMyReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    @Operation(summary = "가게에 리뷰 추가 API", description = "특정 가게에 리뷰를 추가하는 API입니다.")
    public ApiResponse<ReviewResponseDTO.AddResultDTO> addReview(@PathVariable Long restaurantId,
                                                                 @RequestBody @Valid ReviewRequestDTO.AddReviewDTO request) {
        Review review = reviewService.addReview(restaurantId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, ReviewConverter.toAddResultDTO(review));
    }

    @GetMapping("/users/{userId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 유저가 작성한 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<Page<ReviewMyReviewResponse>> getMyReviews(@PathVariable Long userId,
                                                                  @RequestParam(required = false) String restaurantName,
                                                                  @RequestParam(required = false) Integer ratingFloor,
                                                                  @CheckPage @RequestParam(name = "page") Integer page) {
        Page<ReviewMyReviewResponse> response = reviewService.getMyReviews(userId, restaurantName, ratingFloor, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }
}






