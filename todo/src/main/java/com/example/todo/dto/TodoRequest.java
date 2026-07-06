package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequest {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Size(max = 200)
    private String description;

    private boolean completed;
}
