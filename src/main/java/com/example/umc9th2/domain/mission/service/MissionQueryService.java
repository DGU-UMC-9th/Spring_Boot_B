package com.example.umc9th2.domain.mission.service;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import com.example.umc9th2.domain.mission.dto.AvailableMissionDto;
import com.example.umc9th2.domain.mission.dto.MissionStatusDto;
import com.example.umc9th2.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th2.domain.mission.repository.MissionRepository;
import com.example.umc9th2.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc9th2.global.apiPayload.handler.MemberHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository; // 사용자 검증용

    // 1. 도전 가능 미션 목록
    public Page<AvailableMissionDto> getAvailableMissions(String regionName, Long memberId, Pageable pageable) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        return missionRepository.findAvailableMissions(regionName, memberId, pageable);
    }

    // 2. 내가 수행중/완료한 미션 목록
    public Page<MissionStatusDto> getMyMissions(Long memberId, List<Integer> statusList, Pageable pageable) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        return memberMissionRepository.findMyMissionsByStatus(memberId, statusList, pageable);
    }
}
