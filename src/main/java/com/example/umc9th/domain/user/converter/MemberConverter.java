package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.MemberReqDTO;
import com.example.umc9th.domain.user.dto.MemberResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.auth.enums.Role;

public class MemberConverter {

    public static MemberResDTO.JoinResultDTO toJoinResultDTO(User user){
        return new MemberResDTO.JoinResultDTO(user.getId(), user.getCreatedAt());
    }

    // DTO, Salted Password, Role -> Entity
    public static User toUser(
        MemberReqDTO.JoinDTO dto,
        String password,
        Role role
){
    return User.builder()
            .name(dto.name())
            .email(dto.email()) // 추가된 코드
            .password(password) // 추가된 코드
            .role(role)         // 추가된 코드
            .birth(dto.birth())
            .address(dto.address())
            .detailAddress(dto.specAddress())
            .gender(dto.gender())
            .build();
}
}
