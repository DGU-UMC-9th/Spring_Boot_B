package com.example.umc9th.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.example.umc9th.domain.user.enums.Gender;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
        @NotBlank
        String name,
        @Email
        String email, // 추가된 속성
        @NotBlank
        String password, // 추가된 속성
        @NotNull
        Gender gender,
        @NotNull
        LocalDate birth,
        @NotNull
        String address,
        @NotNull
        String specAddress,
        @NotNull
        List<Long> preferCategory
    ){}

    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
