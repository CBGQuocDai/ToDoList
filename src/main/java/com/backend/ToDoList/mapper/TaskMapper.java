package com.backend.ToDoList.mapper;

import com.backend.ToDoList.dto.response.TaskResponse;
import com.backend.ToDoList.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
    }
}
