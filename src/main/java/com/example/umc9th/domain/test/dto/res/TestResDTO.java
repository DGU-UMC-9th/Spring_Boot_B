package com.example.umc9th.domain.test.dto.res;

import lombok.Getter;
import lombok.Builder;
public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testing;
    }
}
