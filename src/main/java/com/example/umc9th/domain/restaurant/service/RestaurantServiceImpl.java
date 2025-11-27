package com.example.umc9th.domain.restaurant.service;

import com.example.umc9th.domain.restaurant.converter.RestaurantConverter;
import com.example.umc9th.domain.restaurant.dto.RestaurantRequestDTO;
import com.example.umc9th.domain.restaurant.entity.Region;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.entity.RestaurantType;
import com.example.umc9th.domain.restaurant.repository.RegionRepository;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.restaurant.repository.RestaurantTypeRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RegionRepository regionRepository;
    private final RestaurantTypeRepository restaurantTypeRepository;

    @Override
    @Transactional
    public Restaurant addRestaurant(Long regionId, RestaurantRequestDTO.AddRestaurantDTO request) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.REGION_NOT_FOUND));

        RestaurantType restaurantType = restaurantTypeRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.FOOD_CATEGORY_NOT_FOUND));

        Restaurant restaurant = RestaurantConverter.toRestaurant(request, region, restaurantType);

        return restaurantRepository.save(restaurant);
    }
}

