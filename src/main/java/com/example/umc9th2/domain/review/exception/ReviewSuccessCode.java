package com.example.umc9th2.domain.review.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor

public class ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.FOUND,
            "REVIEW404_1",
                    "해당 리뷰를 찾는데 성공했습니다.."),
            ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
