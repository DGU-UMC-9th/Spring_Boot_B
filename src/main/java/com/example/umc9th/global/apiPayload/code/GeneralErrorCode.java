package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "AUTH401_1",
            "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "AUTH403_1",
            "요청이 거부되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "요청한 리소스를 찾을 수 없습니다."),
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500_1",
            "예기치 않은 서버 에러가 발생했습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REGION404_1",
            "존재하지 않는 지역입니다."),
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD_CATEGORY404_1",
            "존재하지 않는 음식 카테고리입니다."),
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND,
            "RESTAURANT404_1",
            "존재하지 않는 가게입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "존재하지 않는 유저입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "존재하지 않는 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
