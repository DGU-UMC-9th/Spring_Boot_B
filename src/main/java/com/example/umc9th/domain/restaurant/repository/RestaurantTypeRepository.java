package com.example.umc9th.domain.restaurant.repository;

import com.example.umc9th.domain.restaurant.entity.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {
}

