package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.user.converter.UserMissionConverter;
import com.example.umc9th.domain.user.dto.UserMissionResponseDTO;
import com.example.umc9th.domain.user.entity.UserMission;
import com.example.umc9th.domain.user.enums.MissionStatus;
import com.example.umc9th.domain.user.repository.UserMissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionServiceImpl implements UserMissionService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public Page<UserMissionResponseDTO.UserMissionDTO> getChallengingMissions(Long userId, Integer page) {
        Page<UserMission> userMissionPage = userMissionRepository.findAllByUserIdAndStatus(
                userId,
                MissionStatus.CHALLENGING,
                PageRequest.of(page, 10)
        );
        return userMissionPage.map(UserMissionConverter::toUserMissionDTO);
    }

    @Override
    @Transactional
    public UserMissionResponseDTO.UserMissionDTO completeMission(Long userId, Long missionId) {
        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));

        if (userMission.getStatus() == MissionStatus.COMPLETE) {
            // 이미 완료된 경우 에러 처리 혹은 그대로 반환. 여기서는 그대로 진행 (상태 변경이므로)
        }

        userMission.setStatus(MissionStatus.COMPLETE);
        
        return UserMissionConverter.toUserMissionDTO(userMissionRepository.save(userMission));
    }
}

