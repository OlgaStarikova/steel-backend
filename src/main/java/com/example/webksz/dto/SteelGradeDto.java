package com.example.webksz.dto;

public record SteelGradeDto(
        Long id,
        String name,
        String groupName,
        Boolean hasRecipe
) {
}