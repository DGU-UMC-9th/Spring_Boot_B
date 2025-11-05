package com.example.umc_9th.domain.mission.repository;

import com.example.umc_9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc_9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 쿼리 3: 홈 화면 - 현재 지역에서 도전이 가능한 미션 목록 (페이징 포함)
     */
    @Query("select new com.example.umc_9th.domain.mission.dto.AvailableMissionDto(s.name, m.reward, m.missionSpec, m.deadline) " +
            "from Mission m " +
            "join m.store s " +
            "join s.region r " +
            "where r.name = :regionName " +
            "and not exists (" +
            "  select 1 from MemberMission mm " +
            "  where mm.mission = m and mm.member.id = :memberId" +
            ") " +
            "order by m.deadline asc, m.id asc")
    Page<AvailableMissionDto> findAvailableMissions(
            @Param("regionName") String regionName,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}