package com.example.demo.DTO;

import com.example.demo.entity.Passport;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public record UserDTO (
        @NotNull
        @Size(min = 1, max = 50)
        String name,
        @NotNull
        @Min(0)
        @Max(150)
        int age,

        @NotNull
        Passport passport,
        List<AccountDTO> accounts,
        Set<String> hobbies
){}
