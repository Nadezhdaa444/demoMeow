package com.example.demo.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record AccountDTO(
        @NotNull
        @Size(min = 1, max = 50)
        String title

) {
}
