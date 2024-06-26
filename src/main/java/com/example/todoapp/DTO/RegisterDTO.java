package com.example.todoapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@AllArgsConstructor
@Data
@Builder
public class RegisterDTO {

    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    @Indexed(unique = true)
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 5, max = 255, message = "Password must be between 5 and 255 characters")
    private String password;

    @NotEmpty(message = "Confirm password is required")
    @Size(min = 5, max = 255, message = "Confirm password must be between 5 and 255 characters")
    private String confirmPassword;

}
