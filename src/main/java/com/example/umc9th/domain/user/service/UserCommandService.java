package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.dto.MemberReqDTO;
import com.example.umc9th.domain.user.dto.MemberResDTO;

public interface UserCommandService {
    MemberResDTO.JoinResultDTO joinUser(MemberReqDTO.JoinDTO request);
}

