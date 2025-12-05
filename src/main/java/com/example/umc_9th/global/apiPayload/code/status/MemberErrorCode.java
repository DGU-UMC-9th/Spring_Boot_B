package com.example.umc_9th.global.apiPayload.code.status;

import com.example.umc_9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.global.apiPayload.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 에러 코드 정의
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "사용자를 찾을 수 없습니다."),
    INVALID(HttpStatus.UNAUTHORIZED , "MEMBER404_1", "사용자를 찾을 수 없습니다.");

    // 1. 필드명 통일 (status -> httpStatus)
    // Lombok @Getter가 getHttpStatus()를 만들기 위함입니다.
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    // 2. BaseErrorCode 인터페이스 메서드 구현 (필수)
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
