package com.example.umc9th.global.auth;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 검증할 User 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}

