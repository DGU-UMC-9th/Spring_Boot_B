package com.example.umc_9th.domain.mission.dto;

import lombok.Getter;
import java.time.LocalDate; // LocalDateTime이 아닌 LocalDate를 임포트합니다.

@Getter
public class AvailableMissionDto {
    private String storeName;
    private Long reward;       // Integer가 아닌 Long으로 수정
    private String missionSpec;
    private LocalDate deadline; // LocalDateTime이 아닌 LocalDate로 수정

    // JPQL이 전달하는 (String, Long, String, LocalDate) 타입과
    // 순서, 개수, 타입이 정확히 일치하는 생성자
    public AvailableMissionDto(String storeName, Long reward, String missionSpec, LocalDate deadline) {
        this.storeName = storeName;
        this.reward = reward;
        this.missionSpec = missionSpec;
        this.deadline = deadline;
    }
}