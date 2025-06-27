package com.example.demo.controllers;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API for user registration and login")
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register a new user",
            description = "Endpoint for user registration with required details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("Register was successful");
    }

    @Operation(summary = "Login",  description = "Login for what?", responses = {
            @ApiResponse(responseCode = "200", description = "Login successfully")
    })
    @GetMapping("/me")
    public ResponseEntity<?> whoAmI(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        return ResponseEntity.ok("Current user: " + authentication.getName());
    }
}