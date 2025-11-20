package com.example.umc_9th.domain.member.controller;

import com.example.umc_9th.domain.member.converter.MemberConverter;
import com.example.umc_9th.domain.member.dto.MemberDTO;
import com.example.umc_9th.domain.member.dto.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.MemberResDTO;
import com.example.umc_9th.domain.member.dto.MyPageDto;
import com.example.umc_9th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_9th.domain.member.service.command.MemberCommandService;
import com.example.umc_9th.domain.member.service.query.MemberQueryService;
import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/members")
    public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @GetMapping("/my-page")
    public ApiResponse<MemberDTO.MyPageResponseDTO> getMyPage(
            // TODO: 추후 Spring Security 적용 시 토큰에서 사용자 ID 추출
            @RequestParam(name = "memberId") Long memberId) {

        MyPageDto myPageDto = memberQueryService.getMyPageInfo(memberId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MemberConverter.toMyPageResponseDTO(myPageDto));
    }

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
            ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }
}



