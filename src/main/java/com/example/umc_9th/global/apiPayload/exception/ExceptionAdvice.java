package com.example.umc_9th.global.apiPayload.exception;

import com.example.umc_9th.global.apiPayload.ApiResponse;
import com.example.umc_9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.global.apiPayload.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    /**
     * 1. @Valid 유효성 검사 실패 시 (RequestBody)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
            errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
        });

        return handleExceptionInternalArgs(
                ex,
                HttpHeaders.EMPTY,
                GeneralErrorCode.BAD_REQUEST,
                request,
                errors
        );
    }

    /**
     * 2. 커스텀 예외 처리 (GeneralException)
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(GeneralException ex, WebRequest request) {
        BaseErrorCode errorReason = ex.getCode();
        return handleExceptionInternal(ex, errorReason, null, request);
    }

    /**
     * 3. 그 외 모든 예외 처리 (500 서버 에러 등)
     * ★ 이름 변경: handleException -> handleOtherException
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        ex.printStackTrace(); // 콘솔에 에러 로그 출력
        return handleExceptionInternal(
                ex,
                GeneralErrorCode.INTERNAL_SERVER_ERROR,
                HttpHeaders.EMPTY,
                GeneralErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
                request
        );
    }

    // --- 내부 헬퍼 메서드 ---

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, BaseErrorCode errorCode, HttpHeaders headers, WebRequest request) {
        return handleExceptionInternal(ex, errorCode, headers, errorCode.getReasonHttpStatus().getHttpStatus(), request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, BaseErrorCode errorCode, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), errorCode.getReasonHttpStatus());
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleExceptionInternalArgs(Exception ex, HttpHeaders headers, BaseErrorCode errorCode, WebRequest request, Map<String, String> errorArgs) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), errorArgs);
        return super.handleExceptionInternal(ex, body, headers, errorCode.getReasonHttpStatus().getHttpStatus(), request);
    }

    /**
     * ResponseEntityExceptionHandler의 기본 예외 처리 오버라이딩
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ApiResponse<Object> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                GeneralErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                ex.getMessage()
        );
        return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
    }
}