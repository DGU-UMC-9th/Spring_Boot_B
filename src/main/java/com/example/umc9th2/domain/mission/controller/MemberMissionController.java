package com.example.umc9th2.domain.mission.controller;

import com.example.umc9th2.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc9th2.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc9th2.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th2.domain.mission.entity.MemberMission;
import com.example.umc9th2.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

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
}
