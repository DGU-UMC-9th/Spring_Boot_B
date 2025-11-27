package com.example.umc9th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserMissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionDTO {
        private Long id;
        private Integer reward;
        private Integer goal;
        private LocalDateTime deadline;
        private String missionStatus;
    }
}

