package com.example.umc_9th.domain.member.service.query;

import com.example.umc_9th.domain.member.converter.MemberConverter;
import com.example.umc_9th.domain.member.dto.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.MemberResDTO;
import com.example.umc_9th.domain.member.dto.MyPageDto;
import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.global.apiPayload.code.status.MemberErrorCode;


import com.example.umc_9th.global.apiPayload.handler.MemberHandler;
import com.example.umc_9th.global.auth.entity.CustomUserDetails;
import com.example.umc_9th.global.auth.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;          // 로그인 기능을 위해 추가
    private final PasswordEncoder encoder;  // 로그인 기능을 위해 추가

    // 1. 마이페이지 조회
    public MyPageDto getMyPageInfo(Long memberId) {
        return memberRepository.findMyPageDtoById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    // 2. 로그인 (Impl에 있던 로직을 여기로 통합)
    // 서비스 계층에서는 @Valid를 쓰지 않습니다. Controller에서 이미 검증된 데이터가 넘어옵니다.
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {

        // 이메일로 회원 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            // 비밀번호 틀림 -> 보안상 NOT_FOUND와 동일하게 처리하거나 별도 에러코드 사용
            throw new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        // 토큰 발급
        CustomUserDetails userDetails = new CustomUserDetails(member);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // 응답 DTO 반환
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}