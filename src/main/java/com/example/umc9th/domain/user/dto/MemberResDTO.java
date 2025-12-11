package com.example.umc9th.domain.user.dto;

import java.time.LocalDateTime;
import lombok.Builder;

public class MemberResDTO {
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}

