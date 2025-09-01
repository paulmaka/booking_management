package com.bm.auth_service.controller;

import com.bm.auth_service.dto.LoginRequestDTO;
import com.bm.auth_service.dto.LoginResponseDTO;
import com.bm.auth_service.dto.UserRequestDTO;
import com.bm.auth_service.dto.UserResponseDTO;
import com.bm.auth_service.service.AuthService;
import com.bm.auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService,  UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);

        if (tokenOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(new LoginResponseDTO(tokenOptional.get()));
    }

    @Operation(summary = "Create new account")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Validated({Default.class}) @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        Optional<String> tokenOptional = authService.authenticate(new LoginRequestDTO(userRequestDTO.getEmail(), userRequestDTO.getPassword()));

        if (tokenOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        userResponseDTO.setToken(tokenOptional.get());

        return ResponseEntity.ok().body(userResponseDTO);
    }

    @Operation(summary = "Validate token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
