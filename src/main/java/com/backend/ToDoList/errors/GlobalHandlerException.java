package com.backend.ToDoList.errors;

import com.backend.ToDoList.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalHandlerException {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException e) {
        ApiResponse<?> resp = new ApiResponse<>();
        resp.setMessage(ErrorCode.BAD_REQUEST.getMessage());
        resp.setCode(ErrorCode.BAD_REQUEST.getCode());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<?>> handleAppException(AppException e) {
        ErrorCode error = e.getErrorCode();
        ApiResponse<?> resp = new ApiResponse<>();
        resp.setMessage(error.getMessage());
        resp.setCode(error.getCode());
        return new ResponseEntity<>(resp, error.getHttpStatus());
    }
}
