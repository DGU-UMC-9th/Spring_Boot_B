package com.example.umc_9th.global.apiPayload;


import com.example.umc_9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 (result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
    // 실패 응답 (인자 3개: 코드, 메시지, 데이터) -> ExceptionAdvice가 이걸 사용합니다.
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}
