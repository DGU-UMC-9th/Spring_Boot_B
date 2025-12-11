package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.domain.user.entity.UserMission;
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
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/restaurants/{restaurantId}/missions")
    @Operation(summary = "가게에 미션 추가 API", description = "특정 가게에 미션을 추가하는 API입니다.")
    public ApiResponse<MissionResponseDTO.AddResultDTO> addMission(@PathVariable Long restaurantId,
                                                                   @RequestBody @Valid MissionRequestDTO.AddMissionDTO request) {
        Mission mission = missionService.addMission(restaurantId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, MissionConverter.toAddResultDTO(mission));
    }

    @PostMapping("/missions/{missionId}/challenge")
    @Operation(summary = "미션 도전하기 API", description = "미션을 도전하는 API입니다.")
    public ApiResponse<MissionResponseDTO.ChallengeResultDTO> challengeMission(@PathVariable Long missionId,
                                                                               @RequestBody @Valid MissionRequestDTO.ChallengeMissionDTO request) {
        UserMission userMission = missionService.challengeMission(missionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, MissionConverter.toChallengeResultDTO(userMission));
    }

    @GetMapping("/restaurants/{restaurantId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<Page<MissionResponseDTO.MissionDTO>> getRestaurantMissions(@PathVariable Long restaurantId,
                                                                                  @CheckPage @RequestParam(name = "page") Integer page) {
        Page<MissionResponseDTO.MissionDTO> response = missionService.getRestaurantMissions(restaurantId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }

    @GetMapping("/restaurants/{restaurantId}/missions/slice")
    @Operation(summary = "특정 가게의 미션 목록 조회 API (Slice 버전)", description = "특정 가게의 미션 목록을 Slice 방식으로 조회하는 API입니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다."),
    })
    public ApiResponse<Slice<MissionResponseDTO.MissionDTO>> getRestaurantMissionsSlice(@PathVariable Long restaurantId,
                                                                                        @CheckPage @RequestParam(name = "page") Integer page) {
        Slice<MissionResponseDTO.MissionDTO> response = missionService.getRestaurantMissionsBySlice(restaurantId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }
}

