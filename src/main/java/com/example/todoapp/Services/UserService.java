package com.example.todoapp.Services;

import com.example.todoapp.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByEmail(String email);

    User saveUser(User user);

    UserDetailsService userDetailsService();
}
