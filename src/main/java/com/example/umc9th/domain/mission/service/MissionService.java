package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface MissionService {
    Mission addMission(Long restaurantId, MissionRequestDTO.AddMissionDTO request);
    UserMission challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDTO request);
    Page<MissionResponseDTO.MissionDTO> getRestaurantMissions(Long restaurantId, Integer page);
    Slice<MissionResponseDTO.MissionDTO> getRestaurantMissionsBySlice(Long restaurantId, Integer page);
}

