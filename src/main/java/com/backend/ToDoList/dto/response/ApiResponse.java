package com.backend.ToDoList.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {

    @Builder.Default
    private int code=1000;
    @Builder.Default
    private String message="success";
    private T data;
}
