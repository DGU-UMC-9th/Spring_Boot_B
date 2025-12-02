package com.example.umc9th2.domain.member.repository;


import com.example.umc9th2.domain.member.dto.MyPageDto;
import com.example.umc9th2.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member,Long> {
    List<Member> findByNameAndDeleteAtIsNull(String name);
    Optional<MyPageDto> findMyPageDtoById(Long id);
}
