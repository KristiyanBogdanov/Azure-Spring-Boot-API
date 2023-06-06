package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.LoginRequest;
import org.elsys.health_tracker.controller.resources.RegisterRequest;
import org.elsys.health_tracker.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
