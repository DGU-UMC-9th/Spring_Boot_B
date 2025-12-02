package com.example.umc_9th.domain.store.controller;

import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.converter.StoreConverter;
import com.example.umc_9th.domain.store.dto.StoreResponseDTO;
import com.example.umc_9th.domain.store.service.StoreQueryService;
import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreQueryService storeQueryService;

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요.")
    @GetMapping("/{storeId}/reviews")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ){
        // 사용자가 보낸 page는 1부터 시작한다고 가정 -> Service에 넘길 땐 0부터 시작하게 -1
        Page<Review> reviewPage = storeQueryService.getReviewList(storeId, page - 1);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toReviewPreViewListDTO(reviewPage));
    }

    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요.")
    @GetMapping("/{storeId}/missions")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ){
        Page<Mission> missionPage = storeQueryService.getMissionList(storeId, page - 1);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, StoreConverter.toMissionPreViewListDTO(missionPage));
    }
}