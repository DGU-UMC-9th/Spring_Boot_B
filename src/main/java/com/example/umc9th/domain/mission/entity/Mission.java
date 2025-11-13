package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.store.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Table(name = "mission")

//미션의 속성은 id, reward, deadline, mission_spec, store
//store는 Store 개체와 1대다 관계를 맺는다.
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reward", nullable = false)
    private Long reward;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "mission_spec", nullable = false, length = 255)
    private String missionSpec; //미션 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


}
