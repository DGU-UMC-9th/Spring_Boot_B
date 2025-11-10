package com.example.umc9th.domain.member.dto;

public class MyPageDto {
    private String name;
    private String email;
    private Long point;

    public MyPageDto(String name, String email, Long point) {
        this.name = name;
        this.email = email;
        this.point = point;
    }
}
