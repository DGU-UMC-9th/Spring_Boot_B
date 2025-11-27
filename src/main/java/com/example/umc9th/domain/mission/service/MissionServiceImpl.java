package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionRequestDTO;
import com.example.umc9th.domain.mission.dto.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.UserMission;
import com.example.umc9th.domain.user.repository.UserMissionRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public Mission addMission(Long restaurantId, MissionRequestDTO.AddMissionDTO request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.RESTAURANT_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, restaurant);

        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public UserMission challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDTO request) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));

        // 이미 도전 중인지 체크하는 로직이 있으면 좋겠지만 생략

        UserMission userMission = MissionConverter.toUserMission(mission, user);

        return userMissionRepository.save(userMission);
    }

    @Override
    public Page<MissionResponseDTO.MissionDTO> getRestaurantMissions(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.RESTAURANT_NOT_FOUND));

        Page<Mission> missionPage = missionRepository.findAllByRestaurantId(restaurantId, PageRequest.of(page, 10));
        return missionPage.map(MissionConverter::toMissionDTO);
    }

    @Override
    public Slice<MissionResponseDTO.MissionDTO> getRestaurantMissionsBySlice(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.RESTAURANT_NOT_FOUND));

        Slice<Mission> missionSlice = missionRepository.findSliceByRestaurantId(restaurantId, PageRequest.of(page, 10));
        return missionSlice.map(MissionConverter::toMissionDTO);
    }
}

