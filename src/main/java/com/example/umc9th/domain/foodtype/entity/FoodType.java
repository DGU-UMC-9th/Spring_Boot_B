package com.example.umc9th.domain.foodtype.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FoodType")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String type;
}

