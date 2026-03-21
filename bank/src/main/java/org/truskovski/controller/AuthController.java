package org.truskovski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.truskovski.model.login.dto.LoginDTO;
import org.truskovski.model.register.dto.RegisterDTO;
import org.truskovski.service.AuthService;
import org.truskovski.service.security.RegistrationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO request) {

        registrationService.register(
                request.email(),
                request.password(),
                request.phone(),
                request.fullName()
        );

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO request) {

        authService.login(
                request.email(),
                request.password()
        );

        return ResponseEntity.ok("Login successful");
    }
}