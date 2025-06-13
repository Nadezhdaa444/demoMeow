package com.example.demo.controllers;

import com.example.demo.DTO.HobbyDTO;
import com.example.demo.services.HobbyService;
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
@RequestMapping("/api/Hobby")
public class HobbyController {
    private final HobbyService HobbyService;

    @Operation(
            summary = "Get Hobby by ID",
            description = "Retrieve a specific Hobby by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Hobby found and returned",
                    content = @Content(schema = @Schema(implementation = HobbyDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Hobby not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<HobbyDTO> getHobbyById(@PathVariable Long id) {
        return ResponseEntity.ok().body(HobbyService.findById(id));
    }

    @Operation(
            summary = "Get all Hobbys",
            description = "Retrieve a list of all Hobbys"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of Hobbys",
            content = @Content(schema = @Schema(implementation = HobbyDTO.class)))
    @GetMapping
    public ResponseEntity<List<HobbyDTO>> getHobbys() {
        return ResponseEntity.ok().body(HobbyService.getAll());
    }

    @Operation(
            summary = "Create new Hobby",
            description = "Create a new Hobby record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Hobby created successfully",
                    content = @Content(schema = @Schema(implementation = HobbyDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<HobbyDTO> createHobby(@RequestBody HobbyDTO HobbyDTO) {
        return ResponseEntity.ok().body(HobbyService.create(HobbyDTO));
    }

    @Operation(
            summary = "Update Hobby",
            description = "Update an existing Hobby by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Hobby updated successfully",
                    content = @Content(schema = @Schema(implementation = HobbyDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Hobby not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<HobbyDTO> updateHobby(@PathVariable Long id, @RequestBody HobbyDTO HobbyDTO) {
        return ResponseEntity.ok().body(HobbyService.update(id, HobbyDTO));
    }

    @Operation(
            summary = "Delete Hobby",
            description = "Delete a Hobby by its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Hobby deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Hobby not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HobbyDTO> deleteHobby(@PathVariable Long id) {
        HobbyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}