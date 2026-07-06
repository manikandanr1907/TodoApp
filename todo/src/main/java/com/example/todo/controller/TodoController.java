package com.example.todo.controller;

import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(
            @Valid @RequestBody TodoRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public List<TodoResponse> getAll(){

        return service.getAll();
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id){

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id,
                               @Valid @RequestBody TodoRequest request){

        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok("Deleted Successfully");
    }

}