package com.example.webksz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "recipe_allowed_coef")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeAllowedCoef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coef_id", nullable = false)
    private BalansCoef balansCoef;
}