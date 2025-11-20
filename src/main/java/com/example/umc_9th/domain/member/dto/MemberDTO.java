package com.example.umc_9th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberDTO {

    @Builder
    @Getter
    public static class MyPageResponseDTO{
        private String name;
        private String email;
        private Long point;
    }
}
