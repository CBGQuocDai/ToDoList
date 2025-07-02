package com.backend.ToDoList.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNAUTHORIZED(1001,"Unauthorized", HttpStatus.UNAUTHORIZED),
    BAD_REQUEST(1002,"Bad Request", HttpStatus.BAD_REQUEST),
    TASK_NOT_EXIST(1003,"Task not found ", HttpStatus.NOT_FOUND),
    FORBIDDEN(1004,"Forbidden", HttpStatus.FORBIDDEN),
    EMAIL_EXISTS(1005,"Email already exists", HttpStatus.BAD_REQUEST),
    LOGIN_FAILED(1006,"Email or password is incorrect", HttpStatus.BAD_REQUEST),
    MISSING_FIELD(1007,"missing value ", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1008,"Email is invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1009,"Password must have least 8 characters", HttpStatus.BAD_REQUEST),
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
