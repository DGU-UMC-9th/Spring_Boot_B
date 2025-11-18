package com.example.umc_9th.domain.member.service;

import com.example.umc_9th.domain.member.dto.MyPageDto;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc_9th.global.apiPayload.handler.MemberHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MyPageDto getMyPageInfo(Long memberId) {

        return memberRepository.findMyPageDtoById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
