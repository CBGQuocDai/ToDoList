package com.backend.ToDoList.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UN_AUTHORIZED("Unauthorized", HttpStatus.UNAUTHORIZED),
    ;

    private final String message;
    private final HttpStatus httpStatus;
    ErrorCode (String message, HttpStatus http) {
        this.message = message;
        this.httpStatus = http;
    }
}
