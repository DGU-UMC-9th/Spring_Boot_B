package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //현재 지역에서 내가 수행 가능한 미션의 이름, 보상, 내용, 기한을 가져온다.
    @Query("select new com.example.umc_9th.domain.mission.dto.AvailableMissionDto(s.name, m.reward, m.missionSpec, m.deadline) " +
            "from Mission m " +
            "join m.store s " +
            "join s.region r " +
            //join region으로 가게의 지역을 가져와 내 지역과 비교한다.
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
