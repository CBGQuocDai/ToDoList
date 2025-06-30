package com.backend.ToDoList.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    public AppException(ErrorCode error) {
        super(error.getMessage());
        this.errorCode = error;
    }

}
