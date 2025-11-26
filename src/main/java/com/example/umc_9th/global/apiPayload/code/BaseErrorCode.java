package com.example.umc_9th.global.apiPayload.code;

import com.example.umc_9th.global.apiPayload.dto.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();

    // ★ 아래 두 개 메서드를 추가해야 ExceptionAdvice에서 빨간 줄이 사라집니다.
    String getCode();
    String getMessage();
}

