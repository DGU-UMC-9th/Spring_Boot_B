package com.example.umc_9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengingMissionResponseDTO {
        private Long memberMissionId;
        private String storeName;
        private String missionSpec;
        private Integer reward;
        private LocalDate deadline;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengingMissionListDTO {
        private List<ChallengingMissionResponseDTO> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
