package com.example.todoapp.DTO;


import com.example.todoapp.Annotations.ValueOfEnum;
import com.example.todoapp.Enums.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditTaskDTO {
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long")
    @NotEmpty(message = "Name is required")
    private String name;

    @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters long")
    @Nullable
    private String description;

    @NotEmpty(message = "Status is required")
    @ValueOfEnum(enumClass = TaskStatus.class, message = "Invalid status.")
    private String status;

}
