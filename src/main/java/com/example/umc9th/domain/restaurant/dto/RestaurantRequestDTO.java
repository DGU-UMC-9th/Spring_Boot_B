package com.example.umc9th.domain.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class RestaurantRequestDTO {

    @Getter
    public static class AddRestaurantDTO {
        @NotBlank
        private String name;
        @NotBlank
        private String address;
        @NotNull
        private Long categoryId; // RestaurantType ID
    }
}

