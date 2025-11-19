package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.UserMission;

public interface MissionService {
    Mission addMission(Long restaurantId, MissionRequestDTO.AddMissionDTO request);
    UserMission challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDTO request);
}

