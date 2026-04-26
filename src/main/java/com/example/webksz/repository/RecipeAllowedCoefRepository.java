package com.example.webksz.repository;

import com.example.webksz.model.RecipeAllowedCoef;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeAllowedCoefRepository extends JpaRepository<RecipeAllowedCoef, Long> {
    List<RecipeAllowedCoef> findAll();
}