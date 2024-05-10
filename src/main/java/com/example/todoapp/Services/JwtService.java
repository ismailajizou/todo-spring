package com.example.todoapp.Services;

import jakarta.servlet.http.Cookie;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String extractTokenFromCookie(Cookie[] cookies);

    Cookie makeCookie(String token);

    String cookieRepresentation(Cookie cookie);
}
