package com.example.umc_9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK,
            "OK",
            "성공"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


}
