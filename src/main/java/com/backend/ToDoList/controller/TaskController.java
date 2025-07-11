package com.backend.ToDoList.controller;

import com.backend.ToDoList.dto.apiResponse.BaseResponseTaskListResponse;
import com.backend.ToDoList.dto.apiResponse.BaseResponseTaskResponse;
import com.backend.ToDoList.dto.apiResponse.ErrorResponse;
import com.backend.ToDoList.dto.request.TaskRequest;
import com.backend.ToDoList.dto.response.BaseResponse;
import com.backend.ToDoList.dto.response.ApiResponsePage;
import com.backend.ToDoList.dto.response.TaskResponse;
import com.backend.ToDoList.service.TaskService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TaskController {
    private final TaskService taskService;

    @Operation(
            summary = "add task",
            description = "use to add task to list",
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "create success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponseTaskResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1000,\n" +
                                            "    \"message\": \"success\",\n" +
                                            "    \"data\": {\n" +
                                            "        \"id\": 4,\n" +
                                            "        \"title\": \"Buy tomato\",\n" +
                                            "        \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "    }\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "token is invalid or missing",
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
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "missing title",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1007,\n" +
                                            "    \"message\": \"missing value title\"\n" +
                                            "}"
                            )
                    )
            )
    })
    @PostMapping("")
    public BaseResponse<TaskResponse> createTask(@RequestBody @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskRequest.class),
                            examples = @ExampleObject(
                                    description = "request body include title (title must difference from empty) and description",
                                    value = "{\n" +
                                            "  \"title\": \"Hehehhe\", \n" +
                                            "  \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "}"
                            )
                    )
            )
                                                     TaskRequest req) {
        return BaseResponse.<TaskResponse>builder().data(taskService.createTask(req)).build();
    }
    @Operation(
            summary = "add task",
            description = "use to add task to list",
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "create success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponseTaskResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1000,\n" +
                                            "    \"message\": \"success\",\n" +
                                            "    \"data\": {\n" +
                                            "        \"id\": 4,\n" +
                                            "        \"title\": \"Buy tomato\",\n" +
                                            "        \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "    }\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "token is invalid or missing",
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
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "request miss field title",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1007,\n" +
                                            "    \"message\": \"missing value title\"\n" +
                                            "}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "not found task",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "    \"code\": 1003,\n" +
                                            "    \"message\": \"Task not found \"\n" +
                                            "}"
                            )
                    )
            ),

    })
    @PutMapping("/{id}")
    public BaseResponse<TaskResponse> updateTask(@PathVariable int id, @RequestBody @Valid
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                description = "",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = TaskRequest.class),
                        examples = @ExampleObject(
                                description = "request body include title (title must difference from empty) and description",
                                value = "{\n" +
                                        "  \"title\": \"Hehehhe\", \n" +
                                        "  \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                        "}"
                        )
                )
        )
    TaskRequest req) {
        return BaseResponse.<TaskResponse>builder().data(taskService.updateTask(id,req)).build();
    }
    @Operation(
            summary = "delete task",
            description = "use to delete task from list",
            security = @SecurityRequirement(name = "bearer-key")
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
            ),

    })

    @DeleteMapping("/{id}")
    public BaseResponse<TaskResponse> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return BaseResponse.<TaskResponse>builder().code(204).build();
    }
    @Operation(
            summary = "get task list",
            description = "get task list ",
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "get task list success",
                    content = @Content(
                           mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponseTaskListResponse.class),
                            examples = @ExampleObject(
                                    description = "with page = 1 and limit = 10",
                                    value = "{\n" +
                                            "    \"code\": 1000,\n" +
                                            "    \"message\": \"success\",\n" +
                                            "    \"data\": [\n" +
                                            "        {\n" +
                                            "            \"id\": 1,\n" +
                                            "            \"title\": \"Buy tomato\",\n" +
                                            "            \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"id\": 2,\n" +
                                            "            \"title\": \"Buy tomato\",\n" +
                                            "            \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"id\": 3,\n" +
                                            "            \"title\": \"Buy tomato\",\n" +
                                            "            \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"id\": 4,\n" +
                                            "            \"title\": \"Buy tomato\",\n" +
                                            "            \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"id\": 5,\n" +
                                            "            \"title\": \"\",\n" +
                                            "            \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "        },\n" +
                                            "        {\n" +
                                            "            \"id\": 6,\n" +
                                            "            \"title\": \"\",\n" +
                                            "            \"description\": \"Buy milk, eggs, bread, and cheese\"\n" +
                                            "        }\n" +
                                            "    ],\n" +
                                            "    \"page\": 1,\n" +
                                            "    \"limit\": 10,\n" +
                                            "    \"total\": 6\n" +
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
            ),
    })
    @GetMapping("")
    public ApiResponsePage<List<TaskResponse>> getAllTasks(@RequestParam int page , @RequestParam int limit) {
        List<TaskResponse> res = taskService.getListTasks(page,limit);
        return ApiResponsePage.<List<TaskResponse>>builder()
                .data(res)
                .page(page)
                .limit(limit)
                .total(res.size())
                .build();
    }
}
