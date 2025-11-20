package com.example.umc_9th.domain.test.dto;

import lombok.Builder;
import lombok.Getter;

public class TestDTO {

    @Builder
    @Getter
    public static class TestingResponseDTO {
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}
