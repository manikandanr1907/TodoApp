package com.example.todo;

import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.service.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    TodoServiceImpl service;

    @Mock
    TodoRepository repository;

    @Test
    void createTodo(){

        TodoRequest request=new TodoRequest();

        request.setTitle("Learn Spring");

        request.setDescription("Practice CRUD");

        Todo todo=Todo.builder()
                .id(1L)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        when(repository.save(any())).thenReturn(todo);

        TodoResponse response=service.create(request);

        assertEquals("Learn Spring",response.getTitle());
    }

}