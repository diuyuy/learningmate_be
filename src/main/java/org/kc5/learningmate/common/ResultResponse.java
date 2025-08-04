package org.kc5.learningmate.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class ResultResponse<T> implements Serializable {
    private final int status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public ResultResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ResultResponse(HttpStatus status, T result) {
        this.status = status.value();
        this.message = "success";
        this.result = result;
    }

}

