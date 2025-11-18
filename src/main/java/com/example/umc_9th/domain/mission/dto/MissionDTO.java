package com.example.umc_9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

public class MissionDTO {

    // 1. 도전 가능 미션 목록 응답
    @Builder
    @Getter
    public static class AvailableMissionListDTO {
        private List<AvailableMissionDto> missionList;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    // 2. 내가 수행중/완료한 미션 목록 응답
    @Builder
    @Getter
    public static class MyMissionListDTO {
        private List<MissionStatusDto> missionList;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
