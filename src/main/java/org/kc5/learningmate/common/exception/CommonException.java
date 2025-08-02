package org.kc5.learningmate.domain.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommonException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public CommonException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = httpStatus;
        this.message = errorCode.getMessage();
    }
}
