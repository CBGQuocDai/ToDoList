package com.backend.ToDoList.controller;

import com.backend.ToDoList.dto.request.ChangePasswordRequest;
import com.backend.ToDoList.dto.response.ApiResponse;
import com.backend.ToDoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PutMapping("/changePassword")
    public ApiResponse<Void> changePassword(ChangePasswordRequest req) {
        userService.changePassword(req);
        return ApiResponse.<Void>builder().build();
    }

}
