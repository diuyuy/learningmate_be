package org.kc5.learningmate.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ResultResponse<T> implements Serializable {
    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;


    public ResultResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}