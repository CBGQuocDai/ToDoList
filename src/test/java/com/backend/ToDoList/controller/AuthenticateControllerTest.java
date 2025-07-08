package com.backend.ToDoList.controller;


import com.backend.ToDoList.ToDoListApplication;
import com.backend.ToDoList.dto.request.LoginRequest;
import com.backend.ToDoList.dto.response.TokenResponse;
import com.backend.ToDoList.errors.AppException;
import com.backend.ToDoList.errors.ErrorCode;
import com.backend.ToDoList.service.AuthenticateService;
import com.backend.ToDoList.service.AuthenticateServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ToDoListApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:test.properties")
public class AuthenticateControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private AuthenticateService authenticateService;


    private LoginRequest loginRequest;
    private TokenResponse tokenResponse;
    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@gmail.com");
        loginRequest.setPassword("password");
        tokenResponse = TokenResponse.builder().token("abcd").build();
    }

    @Test
    void testAuthenticate_Success() throws Exception {
        given(authenticateService.handleLogin(any(LoginRequest.class))).willReturn(tokenResponse);
        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("code")
                        .value(1000));
    }
    @Test
    void testAuthenticate_Failure() throws Exception {
        given(authenticateService.handleLogin(any(LoginRequest.class)))
                .willThrow(new AppException(ErrorCode.LOGIN_FAILED));

        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(ErrorCode.LOGIN_FAILED.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Email or password is incorrect"));
    }
}
