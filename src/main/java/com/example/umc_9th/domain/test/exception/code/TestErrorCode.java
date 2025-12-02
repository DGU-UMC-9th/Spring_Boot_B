package com.example.umc_9th.domain.test.exception.code;

import com.example.umc_9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.global.apiPayload.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    // 테스트용 에러 코드
    TEST_ERROR(HttpStatus.BAD_REQUEST, "TEST400", "테스트 에러입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    // 워크북 방식에 맞춰 구현
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