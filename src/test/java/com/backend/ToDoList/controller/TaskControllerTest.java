package com.backend.ToDoList.controller;

import com.backend.ToDoList.ToDoListApplication;
import com.backend.ToDoList.dto.response.TaskResponse;
import com.backend.ToDoList.entity.Task;
import com.backend.ToDoList.entity.User;
import com.backend.ToDoList.service.TaskService;
import com.backend.ToDoList.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {ToDoListApplication.class}
)
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
public class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;
    private List<TaskResponse> tasks;
    @BeforeEach
    public void setup() {
        tasks = new ArrayList<>();
        for (int i=1; i<=5; i++) {
            TaskResponse task = TaskResponse.builder()
                    .id(i)
                    .title("title" + i)
                    .description("description for title " + i).build();
            tasks.add(task);
        }
    }

    @Test
    void testGetAllTasks_Success() throws Exception {
        int page =1;
        int limit = 10;
        UserDetails u= User.builder().email("dai@gmail.com").name("dai").build();

        when(taskService.getListTasks(page,limit)).thenReturn(tasks);
        mvc.perform(MockMvcRequestBuilders.get("/todos")
                .param("page", String.valueOf(page))
                .param("limit", String.valueOf(limit))
                .with(user(u)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1000"))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("data")
                        .isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("page").value(page))
                .andExpect(MockMvcResultMatchers.jsonPath("limit").value(limit))
                .andExpect(MockMvcResultMatchers.jsonPath("total").value(tasks.size()));
    }

    @Test
    void testGetAllTasks_Unauthorized() throws Exception {
        int page =1;
        int limit = 10;
        UserDetails u= User.builder().email("dai@gmail.com").name("dai").build();

        when(taskService.getListTasks(page,limit)).thenReturn(tasks);
        mvc.perform(MockMvcRequestBuilders.get("/todos")
                        .param("page", String.valueOf(page))
                        .param("limit", String.valueOf(limit)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value("1001"))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Unauthorized"));
    }

}
