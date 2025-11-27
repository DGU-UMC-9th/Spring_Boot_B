package com.example.umc9th2.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();

    // 2. BaseErrorCode 인터페이스 메서드 구현 (필수)
}
