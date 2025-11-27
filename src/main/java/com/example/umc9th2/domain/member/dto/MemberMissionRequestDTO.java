package com.example.umc9th2.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberMissionRequestDTO {

    @Getter
    public static class JoinDto {
        @NotNull
        private Long missionId; // 도전할 미션 ID
    }
}