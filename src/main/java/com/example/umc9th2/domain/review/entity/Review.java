package com.example.umc9th2.domain.review.entity;


import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")

//속성은 id, member, store, body, score
//맴버와 store는 둘다 1대다로 받아옴.
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //아이디가 String이면 에러남.
    //id는 기본적으로 새로운 데이터가 들어올 때마다 자동으로 +1씩 하는데, String 형태면 제대로 추가할 수 없다.

    @Column(name = "body", nullable = false, length = 255)
    private String body;

    @Column(name = "score", nullable = false)
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
