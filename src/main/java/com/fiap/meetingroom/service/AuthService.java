package com.fiap.meetingroom.service;

import com.fiap.meetingroom.dto.LoginRequest;
import com.fiap.meetingroom.dto.LoginResponse;
import com.fiap.meetingroom.exception.BadRequestException;
import com.fiap.meetingroom.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            String token = jwtService.gerarToken(request.getUsername());
            return new LoginResponse(token);
        }

        throw new BadRequestException("Usuário ou senha inválidos");
    }
}