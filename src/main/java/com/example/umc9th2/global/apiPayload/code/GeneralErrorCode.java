package com.example.umc9th2.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
//accessLevel는 따로 지정 X

//베이스 에러를 기반으로 에러 코드를 추가 작성
public enum GeneralErrorCode implements BaseErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH401_1","인증이 필요합니다." ),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH403_1", "요청이 거부되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500_1", "예기치 않은 서버 에러가 발생했습니다."),
    // Member Error
    // 사용자 계정 정보가 전달이 안된 경우
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    // 사용자 닉네임이 전달되지 않은 경우
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    // Article Error
    // 기사가 전달되지 않은 경우
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다.");
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

//이거 깃허브 코드와 달라서 수정 필요 (8주차)
