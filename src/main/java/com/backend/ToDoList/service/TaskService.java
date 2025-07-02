package com.backend.ToDoList.service;

import com.backend.ToDoList.dto.request.TaskRequest;
import com.backend.ToDoList.dto.response.TaskResponse;
import com.backend.ToDoList.entity.Task;
import com.backend.ToDoList.entity.User;
import com.backend.ToDoList.errors.AppException;
import com.backend.ToDoList.errors.ErrorCode;
import com.backend.ToDoList.mapper.TaskMapper;
import com.backend.ToDoList.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    public TaskResponse createTask(TaskRequest req) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User user = (User) auth.getPrincipal();
        Task task = Task.builder().title(req.getTitle())
                .description(req.getDescription())
                .user(user).build();
        return taskMapper.toTaskResponse(taskRepository.save(task));
    }
    public TaskResponse updateTask(int id, TaskRequest req) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User user = (User) auth.getPrincipal();
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.TASK_NOT_EXIST));
        if(task.getUser().getId() == user.getId()) {
            task.setTitle(req.getTitle());
            task.setDescription(req.getDescription());
            return taskMapper.toTaskResponse(taskRepository.save(task));
        }
        else {
            throw new AppException(ErrorCode.FORBIDDEN);
        }
    }
    public void deleteTask(int id) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User user = (User) auth.getPrincipal();
        Task task = taskRepository.findById(id).orElse(null);
        if(task==null) return;
        if(task.getUser().getId() == user.getId()) {
            taskRepository.delete(task);
        }
    }

    public List<TaskResponse> getListTasks(int page, int limit) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User user = (User) auth.getPrincipal();
        Pageable pageable = PageRequest.of(page-1, limit);
        List<Task> tasks = taskRepository.findByUser(user, pageable);
        return tasks.stream().map(taskMapper::toTaskResponse).toList();
    }
}
