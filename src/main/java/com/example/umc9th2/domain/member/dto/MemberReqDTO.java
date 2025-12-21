package com.example.umc9th2.domain.member.dto;

import com.example.umc9th2.domain.member.enums.Gender;
import com.example.umc9th2.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String email,
            String password,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}

}
