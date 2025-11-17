package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    //만약 그냥 Exception으로 해버리면, 일일히 Try-Catch문을 작성해야돼서, TestQueryServiceImpl 작성할 때 에러남.
    //Exception은 컴파일러가 일일히 지정해야되는 에러기 때문임.
    //에러가 바로 나는게 GeneralException -> TestException -> TestQueryService -> TestQueryServiceImpl로
    //몇번의 상속 이후 에러가 나기 때문에 원인을 찾기 매우 힘들어진다.

    private final BaseErrorCode code;

}
