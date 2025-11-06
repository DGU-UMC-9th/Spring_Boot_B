package com.example.umc_9th.domain.member.repository;

import com.example.umc_9th.domain.member.dto.MyPageDto;
import com.example.umc_9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<MyPageDto> findMyPageDtoById(Long id);
}