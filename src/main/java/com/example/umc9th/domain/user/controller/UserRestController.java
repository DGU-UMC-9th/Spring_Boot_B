package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.user.dto.MemberReqDTO;
import com.example.umc9th.domain.user.dto.MemberResDTO;
import com.example.umc9th.domain.user.service.UserCommandService;
import com.example.umc9th.domain.user.service.UserQueryService;
import com.example.umc9th.domain.user.dto.UserMissionResponseDTO;
import com.example.umc9th.domain.user.service.UserMissionService;
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
@RequestMapping("/users")
public class UserRestController {

    private final UserMissionService userMissionService;
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 API", description = "회원가입하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<MemberResDTO.JoinResultDTO> join(@RequestBody @Valid MemberReqDTO.JoinDTO request){
        MemberResDTO.JoinResultDTO result = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, result);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "로그인하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<MemberResDTO.LoginDTO> login(@RequestBody @Valid MemberReqDTO.LoginDTO request){
        MemberResDTO.LoginDTO result = userQueryService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, result);
    }

    @GetMapping("/{userId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
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
    public ApiResponse<Page<UserMissionResponseDTO.UserMissionDTO>> getChallengingMissions(@PathVariable Long userId,
                                                                                           @CheckPage @RequestParam(name = "page") Integer page) {
        Page<UserMissionResponseDTO.UserMissionDTO> response = userMissionService.getChallengingMissions(userId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }

    @PatchMapping("/{userId}/missions/{missionId}/complete")
    @Operation(summary = "진행중인 미션 완료 처리 API", description = "진행중인 미션을 완료 상태로 변경하는 API입니다.")
    public ApiResponse<UserMissionResponseDTO.UserMissionDTO> completeMission(@PathVariable Long userId,
                                                                              @PathVariable Long missionId) {
        UserMissionResponseDTO.UserMissionDTO response = userMissionService.completeMission(userId, missionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, response);
    }
}

