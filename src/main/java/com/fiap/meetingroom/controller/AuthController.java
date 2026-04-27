package com.fiap.meetingroom.controller;

import com.fiap.meetingroom.dto.LoginRequest;
import com.fiap.meetingroom.dto.LoginResponse;
import com.fiap.meetingroom.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}