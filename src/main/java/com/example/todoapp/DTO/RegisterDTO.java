package com.example.todoapp.DTO;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDTO {
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters long")
    private String name;

    @Size(min = 3, max = 50, message = "Email must be between 3 and 50 characters long")
    @UniqueElements(message = "Email already exists")
    private String email;

    @Size(min = 6, max = 50, message = "Password must be between 3 and 50 characters long")
    private String password;
}
