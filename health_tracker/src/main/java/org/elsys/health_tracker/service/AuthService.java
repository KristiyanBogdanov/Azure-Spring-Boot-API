package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.AuthResponse;
import org.elsys.health_tracker.controller.resources.LoginRequest;
import org.elsys.health_tracker.controller.resources.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
