package com.example.webksz.repository;

import com.example.webksz.model.SteelRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SteelRecipeRepository extends JpaRepository<SteelRecipe, Long> {
    List<SteelRecipe> findBySteelGradeIdAndIsDeletedFalse(Long steelGradeId);
}