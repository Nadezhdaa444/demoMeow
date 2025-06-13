package com.example.demo.controllers;

import com.example.demo.DTO.UserDTO;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/User")
public class UserController {
    private final UserService UserService;

    @Operation(
            summary = "Get User by ID",
            description = "Retrieve a specific User by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found and returned",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(UserService.findById(id));
    }

    @Operation(
            summary = "Get all Users",
            description = "Retrieve a list of all Users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of Users",
            content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(UserService.getAll());
    }

    @Operation(
            summary = "Create new User",
            description = "Create a new User record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO UserDTO) {
        return ResponseEntity.ok().body(UserService.create(UserDTO));
    }

    @Operation(
            summary = "Update User",
            description = "Update an existing User by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO UserDTO) {
        return ResponseEntity.ok().body(UserService.update(id, UserDTO));
    }

    @Operation(
            summary = "Delete User",
            description = "Delete a User by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "User deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        UserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}