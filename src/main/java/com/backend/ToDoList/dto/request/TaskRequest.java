package com.backend.ToDoList.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    @NotEmpty(message = "MISSING_FIELD")
    private String title;
    private String description;
}
