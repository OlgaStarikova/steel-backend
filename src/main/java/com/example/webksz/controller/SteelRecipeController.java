package com.example.webksz.controller;

import com.example.webksz.dto.SaveSteelRecipesRequestDto;
import com.example.webksz.dto.SteelRecipeDto;
import com.example.webksz.service.SteelRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webksz/steel-grades")
@Validated
public class SteelRecipeController {

    @Autowired
    private SteelRecipeService steelRecipeService;

    @GetMapping("/{steelGradeId}/recipes")
    public ResponseEntity<List<SteelRecipeDto>> getRecipes(@PathVariable Long steelGradeId) {
        return ResponseEntity.ok(steelRecipeService.findBySteelGradeId(steelGradeId));
    }

    @PutMapping("/{steelGradeId}/recipes")
    public ResponseEntity<List<SteelRecipeDto>> saveRecipes(
            @PathVariable Long steelGradeId,
            @Valid @RequestBody SaveSteelRecipesRequestDto requestDto) {
        return ResponseEntity.ok(steelRecipeService.save(steelGradeId, requestDto.recipes()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}