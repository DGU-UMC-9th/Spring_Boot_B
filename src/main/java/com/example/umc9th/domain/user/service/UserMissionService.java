package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.dto.UserMissionResponseDTO;
import org.springframework.data.domain.Page;

public interface UserMissionService {
    Page<UserMissionResponseDTO.UserMissionDTO> getChallengingMissions(Long userId, Integer page);
    UserMissionResponseDTO.UserMissionDTO completeMission(Long userId, Long missionId);
}

