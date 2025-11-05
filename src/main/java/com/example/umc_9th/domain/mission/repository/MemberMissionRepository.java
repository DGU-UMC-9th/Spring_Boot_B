package com.example.umc_9th.domain.mission.repository;



import com.example.umc_9th.domain.mission.dto.MissionStatusDto;
import com.example.umc_9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    @Query("select new com.example.umc_9th.domain.mission.dto.MissionStatusDto(s.name, m.reward, m.missionSpec, mm.status) " +
            "from MemberMission mm " +
            "join mm.mission m " +
            "join m.store s " +
            "where mm.member.id = :memberId and mm.status in :statusList " +
            "order by mm.updatedAt desc")
    Page<MissionStatusDto> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("statusList") List<Integer> statusList,
            Pageable pageable
    );

    @Query("select count(mm.id) " +
            "from MemberMission mm " +
            "join mm.mission m " +
            "join m.store s " +
            "join s.region r " +
            "where mm.member.id = :memberId and r.name = :regionName and mm.status = :status")
    long countCompletedMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionName") String regionName,
            @Param("status") Integer status
    );


    long countByMember_IdAndMission_Store_Region_NameAndStatus(
            Long memberId,
            String regionName,
            Integer status
    );
}