package com.example.todo.controller;

import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Tag(name = "Todo API", description = "CRUD operations for Todo")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "Create Todo")
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(request));
    }

    @Operation(summary = "Get All Todos")
    @GetMapping
    public List<TodoResponse> getAllTodos() {
        return todoService.getAll();
    }

    @Operation(summary = "Get Todo By Id")
    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id) {
        return todoService.getById(id);
    }

    @Operation(summary = "Update Todo")
    @PutMapping("/{id}")
    public TodoResponse updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request) {
        return todoService.update(id, request);
    }

    @Operation(summary = "Delete Todo")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}