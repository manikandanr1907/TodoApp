package com.example.todo;

import com.example.todo.controller.TodoController;
import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.service.TodoService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createTodo() throws Exception{

        TodoRequest request=new TodoRequest();

        request.setTitle("Spring");

        request.setDescription("Demo");

        TodoResponse response=TodoResponse.builder()
                .id(1L)
                .title("Spring")
                .description("Demo")
                .completed(false)
                .build();

        when(service.create(any())).thenReturn(response);

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

}