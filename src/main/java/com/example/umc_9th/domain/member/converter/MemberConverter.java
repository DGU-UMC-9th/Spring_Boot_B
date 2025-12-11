package com.example.umc_9th.domain.member.converter;

import com.example.umc_9th.domain.member.dto.MemberDTO;
import com.example.umc_9th.domain.member.dto.MemberReqDTO;
import com.example.umc_9th.domain.member.dto.MemberResDTO;
import com.example.umc_9th.domain.member.dto.MyPageDto;
import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.global.auth.enums.Role;

public class MemberConverter {

    public static MemberDTO.MyPageResponseDTO toMyPageResponseDTO(MyPageDto myPageDto) {
        return MemberDTO.MyPageResponseDTO.builder()
                .name(myPageDto.getName())
                .email(myPageDto.getEmail())
                .point(myPageDto.getPoint())
                .build();
    }

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

}
