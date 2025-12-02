package com.example.umc_9th.domain.mission.controller;


import com.example.umc_9th.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc_9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc_9th.domain.mission.converter.MemberMissionConverter;

import com.example.umc_9th.domain.mission.converter.MissionConverter;
import com.example.umc_9th.domain.mission.dto.MissionDTO;
import com.example.umc_9th.domain.mission.entity.MemberMission;
import com.example.umc_9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc_9th.domain.mission.service.MemberMissionQueryService;
import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.JoinResultDto> challengeMission(
            @RequestBody @Valid MemberMissionRequestDTO.JoinDto request,
            @RequestParam(name = "memberId") Long memberId // 임시로 쿼리파라미터로 받음
    ) {
        MemberMission memberMission = memberMissionCommandService.challengeMission(request, memberId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberMissionConverter.toJoinResultDTO(memberMission)
        );
    }

    @GetMapping("/members/my")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 현재 도전 중인 미션 목록을 조회합니다.")
    public ApiResponse<MissionDTO.ChallengingMissionListDTO> getMyChallengingMissions(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page") Integer page
    ) {
        Page<MemberMission> missionPage = memberMissionQueryService.getMyChallengingMissions(memberId, page - 1);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MissionConverter.toChallengingMissionListDTO(missionPage));
    }
}
