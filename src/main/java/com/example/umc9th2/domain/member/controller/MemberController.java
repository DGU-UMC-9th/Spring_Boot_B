package com.example.umc9th2.domain.member.controller;

import com.example.umc9th2.domain.member.converter.MemberConverter;
import com.example.umc9th2.domain.member.dto.MemberDTO;
import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;
import com.example.umc9th2.domain.member.dto.MyPageDto;
import com.example.umc9th2.domain.member.service.command.MemberCommandService;
import com.example.umc9th2.domain.member.service.query.MemberQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
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
