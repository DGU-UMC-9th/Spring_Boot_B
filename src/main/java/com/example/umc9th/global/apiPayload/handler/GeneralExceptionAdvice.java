package com.example.umc9th.global.apiPayload.handler;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.notification.dto.DiscordMessage;
import com.example.umc9th.global.notification.service.DiscordNotificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionAdvice {

    private final DiscordNotificationService discordNotificationService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        String errorMessage = Optional.ofNullable(ex.getBindingResult().getFieldError())
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Invalid Request");

        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errorMessage));
    }

    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            GeneralException ex
    ) {

        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        sendDiscordAlert(ex, request);

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }

    private void sendDiscordAlert(Exception ex, HttpServletRequest request) {
        String alertTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String exceptionName = ex.getClass().getSimpleName();
        String exceptionMessage = ex.getMessage();
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();

        String description = String.format(
                "## 🚨 500 Internal Server Error 🚨\n\n" +
                        "**- 발생 시각**: %s\n" +
                        "**- 요청 URI**: %s\n" +
                        "**- HTTP 메서드**: %s\n" +
                        "**- 예외 클래스**: %s\n" +
                        "**- 예외 메시지**: %s\n",
                alertTime, requestUri, requestMethod, exceptionName, exceptionMessage
        );

        DiscordMessage.Embed embed = DiscordMessage.Embed.builder()
                .title("🔥 서버 에러 발생 🔥")
                .description(description)
                .color(15158332) // Red color
                .build();

        DiscordMessage discordMessage = DiscordMessage.builder()
                .content("서버 에러가 발생했습니다.")
                .embeds(new DiscordMessage.Embed[]{embed})
                .build();

        discordNotificationService.sendMessage(discordMessage);
    }
}
