package com.backend.ToDoList.controller;

import com.backend.ToDoList.dto.request.TaskRequest;
import com.backend.ToDoList.dto.response.ApiResponse;
import com.backend.ToDoList.dto.response.TaskResponse;
import com.backend.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("")
    public ApiResponse<TaskResponse> createTask(@RequestBody TaskRequest req) {
        return ApiResponse.<TaskResponse>builder().data(taskService.createTask(req)).build();
    }
    @PutMapping("/{id}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable int id, @RequestBody TaskRequest req) {
        return ApiResponse.<TaskResponse>builder().data(taskService.updateTask(id,req)).build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<TaskResponse> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ApiResponse.<TaskResponse>builder().code(204).build();
    }
    @GetMapping("")
    public ApiResponse<List<TaskResponse>> getAllTasks(@RequestParam int page , @RequestParam int limit) {
        List<TaskResponse> res = taskService.getListTasks(page,limit);
        return ApiResponse.<List<TaskResponse>>builder()
                .data(res)
                .page(page)
                .limit(limit)
                .total(res.size())
                .build();
    }
}
