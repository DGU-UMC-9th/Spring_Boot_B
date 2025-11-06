package com.example.umc_9th.domain.member.dto;

import lombok.Getter;

@Getter
public class MyPageDto {
    private String name;
    private String email;
    private Long point;

    // JPA가 이 생성자를 호출하여 DTO를 만듭니다.
    public MyPageDto(String name, String email, Long point) {
        this.name = name;
        this.email = email;
        this.point = point;
    }
}