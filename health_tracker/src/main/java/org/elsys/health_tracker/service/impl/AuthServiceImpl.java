package org.elsys.health_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.AuthResponse;
import org.elsys.health_tracker.controller.resources.LoginRequest;
import org.elsys.health_tracker.controller.resources.RegisterRequest;
import org.elsys.health_tracker.entity.User;
import org.elsys.health_tracker.exception.InvalidCredentialsException;
import org.elsys.health_tracker.repository.UserRepository;
import org.elsys.health_tracker.service.AuthService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.elsys.health_tracker.mapper.AuthMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ProfileAuditServiceImpl profileAuditService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = MAPPER.fromRegisterRequest(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            User savedUser = userRepository.save(user);
            profileAuditService.afterInsert(savedUser.getProfile());
            return MAPPER.toAuthResponse(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCredentialsException("User with username " + registerRequest.getUsername() + " already exists.");
        }
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username."));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password.");
        } else {
            return MAPPER.toAuthResponse(user);
        }
    }
}
