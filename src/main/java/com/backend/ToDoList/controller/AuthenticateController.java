package com.backend.ToDoList.controller;

import com.backend.ToDoList.dto.apiResponse.BaseResponseTokenResponse;
import com.backend.ToDoList.dto.apiResponse.ErrorResponse;
import com.backend.ToDoList.dto.request.LoginRequest;
import com.backend.ToDoList.dto.request.RegisterRequest;
import com.backend.ToDoList.dto.response.BaseResponse;
import com.backend.ToDoList.dto.response.TokenResponse;
import com.backend.ToDoList.service.AuthenticateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticateController {
    private final AuthenticateService authenticateService;
    @Operation(summary = "Register user",
            description = "Allow client create account"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create success",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponseTokenResponse.class),
                    examples = @ExampleObject(
                            name = "register success",
                            summary = "example for register success",
                            value = "{\n" +
                                    "    \"code\": 1000,\n" +
                                    "    \"message\": \"success\",\n" +
                                    "    \"data\": {\n" +
                                    "        \"token\": \"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYWk1QGdtYWlsLmNvbSIsImp0aSI6IjI2NmI4OTc4LTMzZTMtNGI4OS1hYjFiLWU3Nzc2MTAwMmY0NSIsImlhdCI6MTc1MjIzNjEyMywiZXhwIjoxNzUyMjM2MjIzfQ.buTXcYE0QqkTfZATvlBB03hUPvSX1u_IPUaBnpZbuxtPViTfY13BOZTefwFKbpHrWzFOIPFk25zgRMZ-IcEtXQ\"\n" +
                                    "    }\n" +
                                    "}"
                    )
            )),
            @ApiResponse(responseCode = "400", description = "create fail",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = ErrorResponse.class)
            , examples = {
                    @ExampleObject(
                            name = "email already exist",
                            value= "{\n" +
                                    "    \"code\": 1005,\n" +
                                    "    \"message\": \"Email already exists\"\n" +
                                    "}"),
                    @ExampleObject(
                            name = "password is invalid",
                            description = "password must have least 8 characters",
                            value = "{\n" +
                                    "    \"code\": 1009,\n" +
                                    "    \"message\": \"Password must have least 8 characters\"\n" +
                                    "}"),
                    @ExampleObject(
                            name = "email is invalid",
                            value = "{\n" +
                                    "    \"code\": 1008,\n" +
                                    "    \"message\": \"Email is invalid\"\n" +
                                    "}")
            })),
    })
    @PostMapping("/register")
    public BaseResponse<TokenResponse> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "input for register user",
                    content = @Content(
                            schema = @Schema(implementation = RegisterRequest.class),
                            examples = @ExampleObject(
                                    summary = "",
                                    description = "request include email (email is valid), name and password (password must have least 8 characters)",
                                    value = "{\n" +
                                            "    \"email\": \"dai3gmail.com\",\n" +
                                            "    \"name\" : \"tfasf\",\n" +
                                            "    \"password\": \"12345678\"\n" +
                                            "}"
                            )
                    )
            )
            @RequestBody @Valid RegisterRequest req)
    {
        return BaseResponse.<TokenResponse>builder().data(authenticateService.handleRegister(req)).build();
    }

    @Operation(
            summary = "user login",
            description = "use to login"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login success",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponseTokenResponse.class),
                    examples = @ExampleObject(
                            value = "{\n" +
                                    "    \"code\": 1000,\n" +
                                    "    \"message\": \"success\",\n" +
                                    "    \"data\": {\n" +
                                    "        \"token\": \"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYWk2QGdtYWlsLmNvbSIsImp0aSI6Ijc1YTZmNzcwLWNjMTctNGNkNi1hNzkwLWUyNmY1NjcxOWE5NSIsImlhdCI6MTc1MjI0NDk2OCwiZXhwIjoxNzUyMjQ1MDY4fQ.7cNy_QFr1EhTe4E683KxuWqu6X83EsVaIyMsVO3_xlRx5LSFVsWU1ZJbtwIh33w7MUrBvC234Iz80E8whw2dJQ\"\n" +
                                    "    }\n" +
                                    "}"
                    )
            )),
            @ApiResponse(responseCode = "400",description = "email or password is incorrect",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1006,\n" +
                                            "    \"message\": \"Email or password is incorrect\"\n" +
                                            "}"
                            ))),
    })
    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@RequestBody @Valid
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "input for login",
            content = @Content(
                    schema = @Schema(implementation = LoginRequest.class),
                    examples = @ExampleObject(
                            summary = "",
                            description = "request include email and password",
                            value = "{\n" +
                                    "    \"email\": \"dai6@gmail.com\", \n" +
                                    "    \"password\": \"12345678\"\n" +
                                    "}"
                    )
            )
    )
                                                 LoginRequest req) {
        return BaseResponse.<TokenResponse>builder().data(authenticateService.handleLogin(req)).build();
    }
    @Operation(
            summary = "logout",
            description = "logout account",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "logout" ,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1000,\n" +
                                            "    \"message\": \"success\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "token is invalid or missing" ,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1001,\n" +
                                            "    \"message\": \"Unauthorized\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @PostMapping("/logout")
    public BaseResponse<Void> logout(HttpServletRequest request) {
        authenticateService.handleLogout(request);
        return BaseResponse.<Void>builder().build();
    }
}
