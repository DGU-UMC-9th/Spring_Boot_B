package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.dto.MyPageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member,Long> {
    List<Member> findByNameAndDeletedAtIsNUll(String name);
    Optional<MyPageDto> findMyPageDtoById(Long id);
}
