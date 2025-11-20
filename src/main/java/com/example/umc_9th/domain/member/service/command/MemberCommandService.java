package com.example.umc_9th.domain.member.service.command;

import com.example.umc_9th.domain.member.dto.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
