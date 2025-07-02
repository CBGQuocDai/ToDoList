package com.backend.ToDoList.controller;

import com.backend.ToDoList.dto.request.LoginRequest;
import com.backend.ToDoList.dto.request.RegisterRequest;
import com.backend.ToDoList.dto.response.ApiResponse;
import com.backend.ToDoList.dto.response.TokenResponse;
import com.backend.ToDoList.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ApiResponse<TokenResponse> register(@RequestBody @Valid RegisterRequest req) {
        return ApiResponse.<TokenResponse>builder().data(userService.handleRegister(req)).build();
    }
    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody @Valid LoginRequest req) {
        return ApiResponse.<TokenResponse>builder().data(userService.handleLogin(req)).build();
    }
}
