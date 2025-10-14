package com.example.umc9th.domain.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RestaurantType")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "restaurantType", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();
}

