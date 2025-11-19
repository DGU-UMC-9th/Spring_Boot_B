package com.example.umc9th.domain.mission.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDTO {
        @NotNull
        private Integer reward;
        @NotNull
        private Integer goal;
        @NotNull
        @Future
        private LocalDateTime deadline;
    }

    @Getter
    public static class ChallengeMissionDTO {
        @NotNull
        private Long userId;
    }
}

