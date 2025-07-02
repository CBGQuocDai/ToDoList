package com.backend.ToDoList.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {
    private int id;
    private String title;
    private String description;
}
