package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.UserMission;
import com.example.umc9th.domain.user.enums.MissionStatus;

import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.AddResultDTO toAddResultDTO(Mission mission) {
        return MissionResponseDTO.AddResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(UserMission userMission) {
        return MissionResponseDTO.ChallengeResultDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDTO request, Restaurant restaurant) {
        return Mission.builder()
                .restaurant(restaurant)
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .goal(request.getGoal())
                .build();
    }

    public static UserMission toUserMission(Mission mission, User user) {
        return UserMission.builder()
                .mission(mission)
                .user(user)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .id(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .goal(mission.getGoal())
                .build();
    }
}

