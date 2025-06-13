package com.example.demo.controllers;

import com.example.demo.DTO.PassportDTO;
import com.example.demo.services.PassportService;
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
@RequestMapping("/api/passport")
public class PassportController {
    private final PassportService passportService;

    @Operation(
            summary = "Get passport by ID",
            description = "Retrieve a specific passport by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Passport found and returned",
                    content = @Content(schema = @Schema(implementation = PassportDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passport not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportById(@PathVariable Long id) {
        return ResponseEntity.ok().body(passportService.findById(id));
    }

    @Operation(
            summary = "Get all passports",
            description = "Retrieve a list of all passports"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of passports",
            content = @Content(schema = @Schema(implementation = PassportDTO.class)))
    @GetMapping
    public ResponseEntity<List<PassportDTO>> getPassports() {
        return ResponseEntity.ok().body(passportService.getAll());
    }

    @Operation(
            summary = "Create new passport",
            description = "Create a new passport record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Passport created successfully",
                    content = @Content(schema = @Schema(implementation = PassportDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO) {
        return ResponseEntity.ok().body(passportService.create(passportDTO));
    }

    @Operation(
            summary = "Update passport",
            description = "Update an existing passport by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Passport updated successfully",
                    content = @Content(schema = @Schema(implementation = PassportDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passport not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id, @RequestBody PassportDTO passportDTO) {
        return ResponseEntity.ok().body(passportService.update(id, passportDTO));
    }

    @Operation(
            summary = "Delete passport",
            description = "Delete a passport by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Passport deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passport not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PassportDTO> deletePassport(@PathVariable Long id) {
        passportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}