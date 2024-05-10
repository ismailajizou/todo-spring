package com.example.todoapp.Services;

import com.example.todoapp.DTO.LoginDTO;
import com.example.todoapp.DTO.RegisterDTO;
import com.example.todoapp.Models.User;
import com.example.todoapp.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public String login(LoginDTO data) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
        var user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        return jwtService.generateToken(user);
    }

    @Override
    public User register(
           RegisterDTO user
    ) {
        var newUser = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        newUser = userRepository.save(newUser);
        return newUser;
    }
}
