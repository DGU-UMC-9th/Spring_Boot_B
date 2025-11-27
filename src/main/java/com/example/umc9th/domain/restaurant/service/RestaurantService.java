package com.example.umc9th.domain.restaurant.service;

import com.example.umc9th.domain.restaurant.dto.RestaurantRequestDTO;
import com.example.umc9th.domain.restaurant.entity.Restaurant;

public interface RestaurantService {
    Restaurant addRestaurant(Long regionId, RestaurantRequestDTO.AddRestaurantDTO request);
}

