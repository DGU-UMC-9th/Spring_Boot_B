package com.example.umc9th.domain.restaurant.converter;

import com.example.umc9th.domain.restaurant.dto.RestaurantRequestDTO;
import com.example.umc9th.domain.restaurant.dto.RestaurantResponseDTO;
import com.example.umc9th.domain.restaurant.entity.Region;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.entity.RestaurantType;

import java.time.LocalDateTime;

public class RestaurantConverter {

    public static RestaurantResponseDTO.AddResultDTO toAddResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.AddResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantRequestDTO.AddRestaurantDTO request, Region region, RestaurantType restaurantType) {
        return Restaurant.builder()
                .name(request.getName())
                .location(request.getAddress())
                .region(region)
                .restaurantType(restaurantType)
                .build();
    }
}

