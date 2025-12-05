package com.example.umc_9th.domain.mission.service;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.member.repository.MemberRepository;
import com.example.umc_9th.domain.mission.entity.MemberMission;
import com.example.umc_9th.domain.mission.enums.MissionStatus;
import com.example.umc_9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc_9th.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc_9th.global.apiPayload.handler.MemberHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);

        // 진행 중인(CHALLENGING) 미션만 조회
        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, pageRequest);
    }
}