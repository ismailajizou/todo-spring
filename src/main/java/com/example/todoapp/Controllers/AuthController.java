package com.example.todoapp.Controllers;


import com.example.todoapp.DTO.LoginDTO;
import com.example.todoapp.DTO.RegisterDTO;
import com.example.todoapp.Models.User;
import com.example.todoapp.Services.AuthService;
import com.example.todoapp.Services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        var token = authService.login(loginDTO);
        Cookie cookie = jwtService.makeCookie(token);
        return ResponseEntity.ok().header("Set-Cookie", jwtService.cookieRepresentation(cookie)).build();
    }
}
