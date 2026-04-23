package com.example.webksz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tmc_catalog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmcCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tmc_kl", length = 13)
    private String idTmcKl;

    @Column(name = "vid", nullable = false)
    private Integer vid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "marka", length = 255)
    private String marka;

    @Column(name = "gost", length = 255)
    private String gost;

    @Column(name = "ei", length = 50)
    private String ei;
}