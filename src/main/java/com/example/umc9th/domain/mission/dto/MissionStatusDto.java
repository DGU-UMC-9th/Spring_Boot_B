package com.example.umc9th.domain.mission.dto;

import lombok.Getter;

@Getter
public class MissionStatusDto {
    private String storeName;
    private Long reward; //보상
    private String missionSpec; //내용
    private Long status; //완려 요부

    // 생성자 파라미터를 Long으로 수정
    public MissionStatusDto(String storeName, Long reward, String missionSpec, Long status) {
        this.storeName = storeName;
        this.reward = reward;
        this.missionSpec = missionSpec;
        this.status = status;
    }
}