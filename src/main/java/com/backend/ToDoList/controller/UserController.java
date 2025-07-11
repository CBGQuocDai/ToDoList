package com.backend.ToDoList.controller;

import com.backend.ToDoList.dto.apiResponse.ErrorResponse;
import com.backend.ToDoList.dto.request.ChangePasswordRequest;
import com.backend.ToDoList.dto.response.BaseResponse;
import com.backend.ToDoList.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Operation(
            summary= "change password",
            description = "used to change password if it's necessary",
            security = {@SecurityRequirement(name = "bear-key")}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "change password success",
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
                    responseCode = "400",
                    description = "change password failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "password is invalid",
                                    description = "password must have least 8 characters",
                                    value = "{\n" +
                                            "    \"code\": 1009,\n" +
                                            "    \"message\": \"Password must have least 8 characters\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401", description = "token is invalid or missing" ,
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
    @PutMapping("/changePassword")
    public BaseResponse<Void> changePassword(@RequestBody @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    required = true,
                    description = "input for change password",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ChangePasswordRequest.class),
                            examples = {
                                    @ExampleObject(
                                            summary = "example input",
                                            description = "request body include new password and it must have least 8 characters.",
                                            value = "{\n" +
                                                    "    \"password\":\"123456789\"\n" +
                                                    "}"
                                    )
                            }
                    )
            )
                                                 ChangePasswordRequest req) {
        userService.changePassword(req);
        return BaseResponse.<Void>builder().build();
    }

}
