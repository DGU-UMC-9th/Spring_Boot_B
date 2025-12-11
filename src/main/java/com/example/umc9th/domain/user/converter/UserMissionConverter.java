package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.UserMissionResponseDTO;
import com.example.umc9th.domain.user.entity.UserMission;

public class UserMissionConverter {

    public static UserMissionResponseDTO.UserMissionDTO toUserMissionDTO(UserMission userMission) {
        return UserMissionResponseDTO.UserMissionDTO.builder()
                .id(userMission.getId())
                .reward(userMission.getMission().getReward())
                .goal(userMission.getMission().getGoal())
                .deadline(userMission.getMission().getDeadline())
                .missionStatus(userMission.getStatus().toString())
                .build();
    }
}

