package com.example.webksz.mapper;

import com.example.webksz.config.MapperConfig;
import com.example.webksz.dto.SteelRecipeDto;
import com.example.webksz.model.SteelRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface SteelRecipeMapper {

    @Mapping(source = "tmcCatalog.id", target = "tmcCatalogId")
    @Mapping(source = "tmcCatalog.name", target = "tmcName", defaultValue = "null")
    SteelRecipeDto toDto(SteelRecipe steelRecipe);
}