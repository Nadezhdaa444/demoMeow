package com.example.demo.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PassportDTO(
        @NotNull
             @Size(min = 10, max= 12)
        String number
) {
}
