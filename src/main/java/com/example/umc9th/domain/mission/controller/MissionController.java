package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.domain.user.entity.UserMission;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/restaurants/{restaurantId}/missions")
    public ApiResponse<MissionResponseDTO.AddResultDTO> addMission(@PathVariable Long restaurantId,
                                                                   @RequestBody @Valid MissionRequestDTO.AddMissionDTO request) {
        Mission mission = missionService.addMission(restaurantId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, MissionConverter.toAddResultDTO(mission));
    }

    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeResultDTO> challengeMission(@PathVariable Long missionId,
                                                                               @RequestBody @Valid MissionRequestDTO.ChallengeMissionDTO request) {
        UserMission userMission = missionService.challengeMission(missionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, MissionConverter.toChallengeResultDTO(userMission));
    }
}

