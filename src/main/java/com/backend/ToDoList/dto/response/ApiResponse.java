package com.backend.ToDoList.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse <T> {

    @Builder.Default
    private int code=1000;
    private String message;
    private T data;
}
