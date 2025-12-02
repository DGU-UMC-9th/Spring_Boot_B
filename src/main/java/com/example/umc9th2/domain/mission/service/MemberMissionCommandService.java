package com.example.umc9th2.domain.mission.service;

import com.example.umc9th2.domain.member.dto.MemberMissionRequestDTO;
import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th2.domain.mission.entity.MemberMission;
import com.example.umc9th2.domain.mission.entity.Mission;
import com.example.umc9th2.domain.mission.enums.MissionStatus;
import com.example.umc9th2.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th2.domain.mission.repository.MissionRepository;
import com.example.umc9th2.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc9th2.global.apiPayload.handler.MemberHandler;
import com.example.umc9th2.global.apiPayload.handler.MissionHandler;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public MemberMission challengeMission(MemberMissionRequestDTO.JoinDto request, Long memberId) {

        // 1. 회원 존재 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 미션 존재 확인
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(MissionErrorCode.MISSION_NOT_FOUND));

        // 3. 이미 도전 중인지 확인 (중복 방지)
        boolean isAlreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                member.getId(),
                mission.getId(),
                MissionStatus.CHALLENGING
        );

        if (isAlreadyChallenging) {
            throw new MissionHandler(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        // 4. 매핑 엔티티 생성 및 저장
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        return memberMissionRepository.save(memberMission);
    }
}
