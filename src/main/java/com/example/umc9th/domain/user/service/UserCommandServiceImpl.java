package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.converter.MemberConverter;
import com.example.umc9th.domain.user.dto.MemberReqDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.umc9th.domain.user.dto.MemberResDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MemberResDTO.JoinResultDTO joinUser(MemberReqDTO.JoinDTO request) {
        
        // 이메일 중복 체크
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST); // TODO: 적절한 예외 처리로 변경 필요
        }

        User newUser = MemberConverter.toUser(request, passwordEncoder.encode(request.password()), Role.ROLE_USER);
        
        userRepository.save(newUser);

        return MemberConverter.toJoinResultDTO(newUser);
    }
}

