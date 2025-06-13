package com.example.demo.controllers;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.services.AccountService;
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
@RequestMapping("/api/Account")
public class AccountController {
    private final AccountService AccountService;

    @Operation(
            summary = "Get Account by ID",
            description = "Retrieve a specific Account by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account found and returned",
                    content = @Content(schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok().body(AccountService.findById(id));
    }

    @Operation(
            summary = "Get all Accounts",
            description = "Retrieve a list of all Accounts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of Accounts",
            content = @Content(schema = @Schema(implementation = AccountDTO.class)))
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        return ResponseEntity.ok().body(AccountService.getAll());
    }

    @Operation(
            summary = "Create new Account",
            description = "Create a new Account record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account created successfully",
                    content = @Content(schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO AccountDTO) {
        return ResponseEntity.ok().body(AccountService.create(AccountDTO));
    }

    @Operation(
            summary = "Update Account",
            description = "Update an existing Account by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account updated successfully",
                    content = @Content(schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO AccountDTO) {
        return ResponseEntity.ok().body(AccountService.update(id, AccountDTO));
    }

    @Operation(
            summary = "Delete Account",
            description = "Delete a Account by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Account deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id) {
        AccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}