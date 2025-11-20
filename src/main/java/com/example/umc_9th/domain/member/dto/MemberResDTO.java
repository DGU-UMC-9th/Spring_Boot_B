package com.example.umc_9th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}
