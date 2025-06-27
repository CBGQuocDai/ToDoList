package com.backend.ToDoList.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private int id;
    private String title;
    private String description;
}
