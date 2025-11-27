package com.example.umc9th.domain.restaurant.controller;

import com.example.umc9th.domain.restaurant.converter.RestaurantConverter;
import com.example.umc9th.domain.restaurant.dto.RestaurantRequestDTO;
import com.example.umc9th.domain.restaurant.dto.RestaurantResponseDTO;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.service.RestaurantService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/{regionId}/restaurants")
    public ApiResponse<RestaurantResponseDTO.AddResultDTO> addRestaurant(@PathVariable Long regionId,
                                                                         @RequestBody @Valid RestaurantRequestDTO.AddRestaurantDTO request) {
        Restaurant restaurant = restaurantService.addRestaurant(regionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, RestaurantConverter.toAddResultDTO(restaurant));
    }
}

