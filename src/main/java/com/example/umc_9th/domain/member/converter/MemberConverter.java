package com.example.umc_9th.domain.member.converter;

import com.example.umc_9th.domain.member.dto.MemberDTO;
import com.example.umc_9th.domain.member.dto.MyPageDto;

public class MemberConverter {

    public static MemberDTO.MyPageResponseDTO toMyPageResponseDTO(MyPageDto myPageDto) {
        return MemberDTO.MyPageResponseDTO.builder()
                .name(myPageDto.getName())
                .email(myPageDto.getEmail())
                .point(myPageDto.getPoint())
                .build();
    }
}
