package com.example.umc9th2.domain.member.converter;

import com.example.umc9th2.domain.member.dto.MemberDTO;
import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;
import com.example.umc9th2.domain.member.dto.MyPageDto;
import com.example.umc9th2.domain.member.entity.Member;

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
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .build();
    }

}
