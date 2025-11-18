package com.example.umc_9th.domain.mission.converter;

import com.example.umc_9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc_9th.domain.mission.dto.MissionDTO;
import com.example.umc_9th.domain.mission.dto.MissionStatusDto;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionDTO.AvailableMissionListDTO toAvailableMissionListDTO(Page<AvailableMissionDto> page) {
        return MissionDTO.AvailableMissionListDTO.builder()
                .missionList(page.getContent())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MissionDTO.MyMissionListDTO toMyMissionListDTO(Page<MissionStatusDto> page) {
        return MissionDTO.MyMissionListDTO.builder()
                .missionList(page.getContent())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}