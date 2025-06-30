package com.backend.ToDoList.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNAUTHORIZED(1001,"Unauthorized", HttpStatus.UNAUTHORIZED),
    BAD_REQUEST(1002,"Bad Request", HttpStatus.BAD_REQUEST),
    ;
    private final int code ;
    private final String message;
    private final HttpStatus httpStatus;
    ErrorCode (int code ,String message, HttpStatus http) {
        this.code = code;
        this.message = message;
        this.httpStatus = http;
    }
}
