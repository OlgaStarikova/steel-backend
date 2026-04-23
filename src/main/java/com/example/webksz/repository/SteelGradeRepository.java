package com.example.webksz.repository;

import com.example.webksz.model.SteelGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SteelGradeRepository extends JpaRepository<SteelGrade, Long> {
    List<SteelGrade> findByGroupId(Long groupId);
}