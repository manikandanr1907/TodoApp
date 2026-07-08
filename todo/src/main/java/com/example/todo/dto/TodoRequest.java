package com.example.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequest {

    @Schema(description = "Todo title", example = "Learn Spring Boot")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Schema(description = "Todo description", example = "Complete CRUD project")
    @Size(max = 200)
    private String description;

    @Schema(description = "Completion status", example = "false")
    private boolean completed;


}
