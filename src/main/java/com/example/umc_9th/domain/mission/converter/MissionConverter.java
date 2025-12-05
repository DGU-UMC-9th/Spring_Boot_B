package com.example.umc_9th.domain.mission.converter;

import com.example.umc_9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc_9th.domain.mission.dto.MissionDTO;
import com.example.umc_9th.domain.mission.dto.MissionStatusDto;
import com.example.umc_9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    // MemberMission -> DTO 변환
    public static MissionDTO.ChallengingMissionResponseDTO toChallengingMissionDTO(MemberMission memberMission) {
        return MissionDTO.ChallengingMissionResponseDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName()) // 연관관계 탐색
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward().intValue())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    // Page -> List DTO 변환
    public static MissionDTO.ChallengingMissionListDTO toChallengingMissionListDTO(Page<MemberMission> page) {
        List<MissionDTO.ChallengingMissionResponseDTO> dtoList = page.stream()
                .map(MissionConverter::toChallengingMissionDTO)
                .collect(Collectors.toList());

        return MissionDTO.ChallengingMissionListDTO.builder()
                .missionList(dtoList)
                .listSize(dtoList.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}