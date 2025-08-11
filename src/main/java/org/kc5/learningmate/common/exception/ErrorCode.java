package org.kc5.learningmate.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400
    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "잘못된 요청 형식입니다."),

    //401
    UNAUTHORIZED(40100, HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    INVALID_PASSWORD(40101, HttpStatus.UNAUTHORIZED, "비밀번호가 올바르지 않습니다."),
    INVALID_REFRESH_TOKEN(40102, HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh Token입니다."),
    INVALID_PIN(40103, HttpStatus.UNAUTHORIZED, "PIN 번호가 올바르지 않습니다."),
    INVALID_AUTH_FORMAT(40104, HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 요청 형식입니다."),

    //403
    FORBIDDEN(40300, HttpStatus.FORBIDDEN, "요청 권한이 없습니다."),

    // 404
    NOT_FOUND(40400, HttpStatus.NOT_FOUND, "존재하지 않는 자원입니다."),
    MEMBER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    ARTICLE_NOT_FOUND(40402, HttpStatus.NOT_FOUND, "존재하지 않는 기사입니다."),
    REVIEW_NOT_FOUND(40403, HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),
    KEYWORD_NOT_FOUND(40404, HttpStatus.NOT_FOUND, "해당 기간의 키워드가 존재하지 않습니다."),

    //409
    DUPLICATE_REVIEW(40900, HttpStatus.CONFLICT, "기사에 대한 리뷰를 이미 작성했습니다."),

    // 500
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버내부 오류입니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
