package com.example.umc9th.domain.foodtype.repository;

import com.example.umc9th.domain.foodtype.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
}

