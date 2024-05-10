package com.example.todoapp.Services;

import com.example.todoapp.DTO.LoginDTO;
import com.example.todoapp.DTO.RegisterDTO;
import com.example.todoapp.Models.User;

public interface AuthService {
    String login(LoginDTO data);

    User register(
            RegisterDTO user
    );
}
