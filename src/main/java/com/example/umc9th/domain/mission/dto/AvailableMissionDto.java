package com.example.umc9th.domain.mission.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AvailableMissionDto {
    private String storeName;
    private Long reward;
    private String missionSpec;
    private LocalDate deadline;

    public AvailableMissionDto(String storeName, Long reward, String missionSpec, LocalDate deadline) {
        this.storeName = storeName;
        this.reward = reward;
        this.missionSpec = missionSpec;
        this.deadline = deadline;
    }

}
