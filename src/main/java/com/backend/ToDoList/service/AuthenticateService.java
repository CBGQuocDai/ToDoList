package com.backend.ToDoList.service;

import com.backend.ToDoList.dto.request.LoginRequest;
import com.backend.ToDoList.dto.request.RegisterRequest;
import com.backend.ToDoList.dto.response.TokenResponse;
import com.backend.ToDoList.entity.User;
import com.backend.ToDoList.errors.AppException;
import com.backend.ToDoList.errors.ErrorCode;
import com.backend.ToDoList.mapper.UserMapper;
import com.backend.ToDoList.repository.UserRepository;
import com.backend.ToDoList.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticateService  {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String,Object> redisTemplate;
    public TokenResponse handleLogin(LoginRequest req) {
        User u = null;
        try {
            u = userRepository.getByEmail(req.getEmail());
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        if(passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            return TokenResponse.builder().token(jwtUtils.generateToken(u.getEmail())).build();
        }
        else{
            throw new AppException(ErrorCode.LOGIN_FAILED);
        }
    }
    public TokenResponse handleRegister(RegisterRequest req) {
        User u = userMapper.toUser(req);
        log.info(String.valueOf(userRepository.findByEmail(u.getEmail()) !=null));
        if(userRepository.findByEmail(u.getEmail()) !=null) {
            throw new AppException(ErrorCode.EMAIL_EXISTS);
        }
        User user = userRepository.save(u);
        String token = jwtUtils.generateToken(user.getEmail());
        log.info(token);
        return TokenResponse.builder().token(token).build();
    }

    public void handleLogout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Claims body = jwtUtils.getContent(token);
            String jit = body.getId();
            long ttl = body.getExpiration().getTime()  - new Date().getTime();
            if(ttl>0) {
                redisTemplate.opsForValue().set(jit,"BlackList" ,ttl, TimeUnit.MILLISECONDS);
            }
        }
    }
}
