package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.UserMission;
import com.example.umc9th.domain.user.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findAllByUserIdAndStatus(Long userId, MissionStatus status, Pageable pageable);
    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}

