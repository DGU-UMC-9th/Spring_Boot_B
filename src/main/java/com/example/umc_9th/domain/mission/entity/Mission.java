package com.example.umc_9th.domain.mission.entity;

import com.example.umc_9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reward", nullable = false)
    private Long reward;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "mission_spec", nullable = false, length = 255)
    private String mission_spec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
