package com.example.umc_9th.domain.mission.controller;

import com.example.umc_9th.domain.mission.converter.MissionConverter;
import com.example.umc_9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc_9th.domain.mission.dto.MissionDTO;
import com.example.umc_9th.domain.mission.dto.MissionStatusDto;
import com.example.umc_9th.domain.mission.service.MissionQueryService;
import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    // 1. 도전 가능 미션 목록 (홈)
    @GetMapping("/available")
    public ApiResponse<MissionDTO.AvailableMissionListDTO> getAvailableMissions(
            // TODO: 추후 Spring Security 적용 시 토큰에서 사용자 ID 추출
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam String regionName, // TODO: 지역명은 사용자의 현재 위치 기준?
            @PageableDefault(size = 10) Pageable pageable) {

        Page<AvailableMissionDto> missionPage = missionQueryService.getAvailableMissions(regionName, memberId, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MissionConverter.toAvailableMissionListDTO(missionPage));
    }

    // 2. 내가 수행중/완료한 미션 목록 (마이페이지)
    @GetMapping("/my")
    public ApiResponse<MissionDTO.MyMissionListDTO> getMyMissions(
            // TODO: 추후 Spring Security 적용 시 토큰에서 사용자 ID 추출
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam List<Integer> statusList, // 예: /my?statusList=1&statusList=2
            @PageableDefault(size = 10) Pageable pageable) {

        Page<MissionStatusDto> missionPage = missionQueryService.getMyMissions(memberId, statusList, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MissionConverter.toMyMissionListDTO(missionPage));
    }
}