package com.example.webksz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "steel_recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SteelRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "steel_grade_id", nullable = false)
    private SteelGrade steelGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tmc_catalog_id")
    private TmcCatalog tmcCatalog;

    @Column(name = "section_id", nullable = false)
    private Integer sectionId;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "percentage", precision = 10, scale = 3)
    private BigDecimal percentage;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "formula_id")
    private Integer formulaId;

    @Column(name = "coefficient", length = 100)
    private String coefficient;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}