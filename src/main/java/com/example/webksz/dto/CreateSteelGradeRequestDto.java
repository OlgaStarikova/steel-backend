package com.example.webksz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSteelGradeRequestDto(
        @NotBlank
        String name,

        @NotNull @Positive
        Long groupId
) {
}