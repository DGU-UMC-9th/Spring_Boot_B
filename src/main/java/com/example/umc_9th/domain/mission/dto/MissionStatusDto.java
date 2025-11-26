package com.example.umc_9th.domain.mission.dto;


import com.example.umc_9th.domain.mission.enums.MissionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionStatusDto {
    private String storeName;
    private Long reward;
    private String missionSpec;
    private String status;


    public MissionStatusDto(String storeName, Long reward, String missionSpec, MissionStatus status) {
        this.storeName = storeName;
        this.reward = reward;
        this.missionSpec = missionSpec;
        this.status = status.toString();
    }
}