package com.example.umc9th2.global.apiPayload.code.status;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    // 에러 코드 정의
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "가게를 찾을 수 없습니다.");

    // 필드명 통일 (httpStatus)
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    // --- BaseErrorCode 인터페이스 구현 ---

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
