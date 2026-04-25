package com.example.webksz.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public record SaveSteelRecipesRequestDto(
        @NotNull
        Long steelGradeId,

        @NotNull
        List<@Valid RecipeItemDto> recipes
) {
        public record RecipeItemDto(
                Long id,
                @NotNull @Positive
                Integer sectionId,
                @NotNull @Min(0)
                Integer sortOrder,
                Long tmcCatalogId,
                BigDecimal percentage,
                String note,
                String coefficient
        ) {
        }
}