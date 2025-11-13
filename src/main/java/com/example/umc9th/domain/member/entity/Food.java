package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.FoodName;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Table(name = "food")

public class Food extends BaseEntity {
//음식의 속성은 id, name

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodName name; //얘는 nullable해서 = FoodName.NONE 이런식으로 지정 필요 X

}
