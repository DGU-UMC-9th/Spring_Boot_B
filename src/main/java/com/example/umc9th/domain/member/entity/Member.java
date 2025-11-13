package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.store.enums.Address;
import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.global.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
//이거 3개는 고정

@NoArgsConstructor(access = AccessLevel.PROTECTED)
//생성자를 자동으로 만들어주는 어노테이션
//access를 이용해 개발자가 직접호출하는 문제를 방지해줌
@AllArgsConstructor(access = AccessLevel.PRIVATE)

@Table(name = "member")

//Table 어노테이션 덕분에 이 클래스는 테이블 취급
//안에다 속성들을 설정해주면 됨.
public class Member extends BaseEntity {
    //맴버의 속성은 id, 이름, 성별, 생년월일
    //5주차 주소, 상세주소, 소셜UID, 소설타입, 포인트, 이메일, 전화번호 속성 추가

    //기본키 속성은 GeneratedValue로 따로 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE; //젠더는 enums에서 따로 설정 후 NONE로 기본값 설정
    //만약 nullable = true로 하면 기본값 세팅 안해도 됨.

    @Column(name = "birth", nullable = false)
    private LocalDate birth;


    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "social_uid", nullable = false)
    private String socialUid;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;


}
