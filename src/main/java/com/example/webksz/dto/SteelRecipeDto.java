package com.example.webksz.dto;

import java.math.BigDecimal;

public record SteelRecipeDto(
        Long id,
        Integer sectionId,
        Integer sortOrder,
        Long tmcCatalogId,
        String tmcName,
        BigDecimal percentage,
        String note,
        String coefficient
) {
}