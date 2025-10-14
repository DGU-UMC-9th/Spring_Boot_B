package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Notification")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_checked")
    private Boolean isChecked;

    @Column(length = 20)
    private String type;

    @Column(length = 30)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;
}

