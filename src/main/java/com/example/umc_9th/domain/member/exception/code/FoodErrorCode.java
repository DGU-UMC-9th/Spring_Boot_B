package com.example.umc_9th.domain.member.exception.code;

import com.example.umc_9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.global.apiPayload.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    // 예시 에러 코드 (기존에 있던 것 유지)
    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD404_1", "해당 음식을 찾을 수 없습니다.");

    // 1. 필드명 변경 (status -> httpStatus)
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    // 2. BaseErrorCode 메서드 구현
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}