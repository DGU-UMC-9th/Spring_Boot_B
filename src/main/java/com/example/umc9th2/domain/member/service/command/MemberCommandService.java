package com.example.umc9th2.domain.member.service.command;

import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
