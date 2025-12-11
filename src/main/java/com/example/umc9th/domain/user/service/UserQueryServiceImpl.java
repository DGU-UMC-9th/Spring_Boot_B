package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.dto.MemberReqDTO;
import com.example.umc9th.domain.user.dto.MemberResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.auth.CustomUserDetails;
import com.example.umc9th.global.auth.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO request) {
        // 1. 이메일로 유저 찾기
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST); // 비밀번호 불일치
        }

        // 3. 토큰 생성
        String accessToken = jwtUtil.createAccessToken(new CustomUserDetails(user));

        return MemberResDTO.LoginDTO.builder()
                .memberId(user.getId())
                .accessToken(accessToken)
                .build();
    }
}

