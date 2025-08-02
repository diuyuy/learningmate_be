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

    // 500
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버내부 오류입니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
