package com.example.umc_9th.domain.member.dto;

import com.example.umc_9th.domain.member.enums.Gender;
import com.example.umc_9th.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}

}
