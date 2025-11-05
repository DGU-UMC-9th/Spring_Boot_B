package com.example.umc_9th.domain.mission.dto;

import lombok.Getter;

@Getter
public class MissionStatusDto {
    private String storeName;
    private Long reward; // Integer -> Long
    private String missionSpec;
    private Long status; // Integer -> Long

    // 생성자 파라미터를 Long으로 수정
    public MissionStatusDto(String storeName, Long reward, String missionSpec, Long status) {
        this.storeName = storeName;
        this.reward = reward;
        this.missionSpec = missionSpec;
        this.status = status;
    }
}