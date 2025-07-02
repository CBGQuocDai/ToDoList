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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        try {
            User u = userRepository.getByEmail(email);
//            log.info(u.getEmail());
            return u;
        }catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
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
}
