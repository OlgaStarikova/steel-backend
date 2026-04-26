package com.example.webksz.repository;

import com.example.webksz.model.BalansCoef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalansCoefRepository extends JpaRepository<BalansCoef, Long> {
    BalansCoef findByCode(String code);
}