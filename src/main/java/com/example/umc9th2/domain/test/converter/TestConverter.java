package com.example.umc9th2.domain.test.converter;


import com.example.umc9th2.domain.test.dto.res.TestResDto;

public class TestConverter {

    //객체 -> DTO
    public static TestResDto.Testing toTestingDTO(String testing) {
        return TestResDto.Testing.builder()
                .testing(testing)
                .build();
    }
    //testing이 노션에는 testString(testing)으로 되어 있는데 에러가 나서 바꿈,

    // 객체 -> DTO
    public static TestResDto.Exception toExceptionDTO(
            String testing
    ){
        return TestResDto.Exception.builder()
                .testString(testing)
                .build();
    }
}
