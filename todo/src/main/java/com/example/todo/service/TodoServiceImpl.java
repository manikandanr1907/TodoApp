package com.example.todo.service;

import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;
import com.example.todo.entity.Todo;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Override
    public TodoResponse create(TodoRequest request){

        Todo todo=Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.isCompleted())
                .build();

        repository.save(todo);

        return map(todo);
    }

    @Override
    public List<TodoResponse> getAll(){

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public TodoResponse getById(Long id){

        Todo todo=repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo Not Found"));

        return map(todo);
    }

    @Override
    public TodoResponse update(Long id,TodoRequest request){

        Todo todo=repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo Not Found"));

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());

        repository.save(todo);

        return map(todo);
    }

    @Override
    public void delete(Long id){

        Todo todo=repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo Not Found"));

        repository.delete(todo);

    }

    private TodoResponse map(Todo todo){

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .build();
    }

}
