package com.example.umc_9th.domain.test.converter;

import com.example.umc_9th.domain.test.dto.TestDTO;

public class TestConverter {

    public static TestDTO.TestingResponseDTO toTestiingDTO(
            String testing
    ){
        return TestDTO.TestingResponseDTO.builder()
                .testing(testing).build();
    }

    public static TestDTO.Exception toExceptionDTO(
            String testing
    ){
        return TestDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
