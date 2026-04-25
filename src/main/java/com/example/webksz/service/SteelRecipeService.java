package com.example.webksz.service;

import com.example.webksz.dto.SaveSteelRecipesRequestDto.RecipeItemDto;
import com.example.webksz.dto.SteelRecipeDto;
import com.example.webksz.mapper.SteelRecipeMapper;
import com.example.webksz.model.SteelGrade;
import com.example.webksz.model.SteelRecipe;
import com.example.webksz.model.TmcCatalog;
import com.example.webksz.repository.SteelGradeRepository;
import com.example.webksz.repository.SteelRecipeRepository;
import com.example.webksz.repository.TmcCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SteelRecipeService {

    private final SteelRecipeRepository steelRecipeRepository;
    private final SteelGradeRepository steelGradeRepository;
    private final TmcCatalogRepository tmcCatalogRepository;
    private final SteelRecipeMapper steelRecipeMapper;

    public List<SteelRecipeDto> findBySteelGradeId(Long steelGradeId) {
        return steelRecipeRepository.findBySteelGradeIdAndIsDeletedFalse(steelGradeId)
                .stream()
                .sorted((a, b) -> {
                    int cmp = a.getSectionId().compareTo(b.getSectionId());
                    if (cmp != 0) return cmp;
                    return a.getSortOrder().compareTo(b.getSortOrder());
                })
                .map(steelRecipeMapper::toDto)
                .toList();
    }

    @Transactional
    public List<SteelRecipeDto> save(Long steelGradeId, List<RecipeItemDto> recipes) {
        SteelGrade steelGrade = steelGradeRepository.findById(steelGradeId)
                .orElseThrow(() -> new IllegalArgumentException("SteelGrade not found: " + steelGradeId));

        List<SteelRecipe> existing = steelRecipeRepository.findBySteelGradeIdAndIsDeletedFalse(steelGradeId);
        Map<Long, SteelRecipe> existingMap = existing.stream()
                .collect(Collectors.toMap(SteelRecipe::getId, Function.identity()));

        List<Long> incomingIds = new ArrayList<>();

        for (RecipeItemDto dto : recipes) {
            if (dto.id() != null && existingMap.containsKey(dto.id())) {
                SteelRecipe recipe = existingMap.get(dto.id());
                recipe.setSectionId(dto.sectionId());
                recipe.setSortOrder(dto.sortOrder());
                recipe.setPercentage(dto.percentage());
                recipe.setNote(dto.note());
                recipe.setCoefficient(dto.coefficient());

                if (dto.tmcCatalogId() != null) {
                    TmcCatalog tmc = tmcCatalogRepository.findById(dto.tmcCatalogId()).orElse(null);
                    recipe.setTmcCatalog(tmc);
                } else {
                    recipe.setTmcCatalog(null);
                }

                steelRecipeRepository.save(recipe);
                incomingIds.add(dto.id());
            } else {
                SteelRecipe recipe = new SteelRecipe();
                recipe.setSteelGrade(steelGrade);
                recipe.setSectionId(dto.sectionId());
                recipe.setSortOrder(dto.sortOrder());
                recipe.setPercentage(dto.percentage());
                recipe.setNote(dto.note());
                recipe.setCoefficient(dto.coefficient());

                if (dto.tmcCatalogId() != null) {
                    TmcCatalog tmc = tmcCatalogRepository.findById(dto.tmcCatalogId()).orElse(null);
                    recipe.setTmcCatalog(tmc);
                }

                recipe.setIsDeleted(false);
                recipe = steelRecipeRepository.save(recipe);
                incomingIds.add(recipe.getId());
            }
        }

        for (SteelRecipe recipe : existing) {
            if (!incomingIds.contains(recipe.getId())) {
                recipe.setIsDeleted(true);
                steelRecipeRepository.save(recipe);
            }
        }

        return findBySteelGradeId(steelGradeId);
    }
}