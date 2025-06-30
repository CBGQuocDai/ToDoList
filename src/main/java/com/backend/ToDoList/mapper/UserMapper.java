package com.backend.ToDoList.mapper;


import com.backend.ToDoList.dto.request.RegisterRequest;
import com.backend.ToDoList.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public User toUser(RegisterRequest req) {
        return User.builder().email(req.getEmail())
                .name(req.getName())
                .password(passwordEncoder.encode(req.getPassword())).build();
    }
}
