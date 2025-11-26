package com.example.umc_9th.domain.mission.converter;


import com.example.umc_9th.domain.member.dto.MemberMissionResponseDTO;
import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.mission.entity.MemberMission;
import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.JoinResultDto toJoinResultDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.JoinResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now()) // Auditing 적용 시 memberMission.getCreatedAt()
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING) // 초기 상태는 진행 중
                .build();
    }
}