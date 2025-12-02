package com.example.umc9th2.domain.member.service.query;

import com.example.umc9th2.domain.member.dto.MyPageDto;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc9th2.global.apiPayload.handler.MemberHandler;
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