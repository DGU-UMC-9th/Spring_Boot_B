package com.example.umc_9th.domain.member.controller;

import com.example.umc_9th.domain.member.converter.MemberConverter;
import com.example.umc_9th.domain.member.dto.MemberDTO;
import com.example.umc_9th.domain.member.dto.MyPageDto;
import com.example.umc_9th.domain.member.service.MemberQueryService;
import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.GeneralSuccessCode;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/members")
    public class MemberController {

        private final MemberQueryService memberQueryService;

        @GetMapping("/my-page")
        public ApiResponse<MemberDTO.MyPageResponseDTO> getMyPage(
                // TODO: 추후 Spring Security 적용 시 토큰에서 사용자 ID 추출
                @RequestParam(name = "memberId") Long memberId) {

            MyPageDto myPageDto = memberQueryService.getMyPageInfo(memberId);

            return ApiResponse.onSuccess(GeneralSuccessCode.OK, MemberConverter.toMyPageResponseDTO(myPageDto));
        }
}
