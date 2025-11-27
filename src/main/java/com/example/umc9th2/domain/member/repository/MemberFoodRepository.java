package com.example.umc9th2.domain.member.repository;

import com.example.umc9th2.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
