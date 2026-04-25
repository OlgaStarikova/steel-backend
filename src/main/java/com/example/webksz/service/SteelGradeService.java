package com.example.webksz.service;

import com.example.webksz.dto.CreateSteelGradeRequestDto;
import com.example.webksz.dto.SteelGradeDto;
import com.example.webksz.mapper.SteelGradeMapper;
import com.example.webksz.model.SteelGrade;
import com.example.webksz.model.SteelGradesGroup;
import com.example.webksz.repository.SteelGradeRepository;
import com.example.webksz.repository.SteelGradesGroupRepository;
import com.example.webksz.repository.SteelRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SteelGradeService {

    private final SteelGradeRepository steelGradeRepository;
    private final SteelGradesGroupRepository steelGradesGroupRepository;
    private final SteelRecipeRepository steelRecipeRepository;
    private final SteelGradeMapper steelGradeMapper;

    public List<SteelGradeDto> findAll() {
        return steelGradeRepository.findAll()
                .stream()
                .map(steelGrade -> {
                    boolean hasRecipe = steelRecipeRepository.existsBySteelGradeIdAndIsDeletedFalse(steelGrade.getId());
                    return steelGradeMapper.toDto(steelGrade, hasRecipe);
                })
                .toList();
    }

    @Transactional
    public SteelGradeDto create(CreateSteelGradeRequestDto requestDto) {
        if (requestDto.groupId() == null) {
            throw new IllegalArgumentException("groupId must not be null");
        }
        if (requestDto.name() == null || requestDto.name().isBlank()) {
            throw new IllegalArgumentException("name must not be empty");
        }

        SteelGradesGroup group = steelGradesGroupRepository
                .findById(requestDto.groupId())
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + requestDto.groupId()));

        SteelGrade steelGrade = new SteelGrade();
        steelGrade.setName(requestDto.name());
        steelGrade.setGroup(group);

        steelGrade = steelGradeRepository.save(steelGrade);
        return steelGradeMapper.toDto(steelGrade);
    }

    @Transactional
    public void delete(Long id) {
        steelGradeRepository.deleteById(id);
    }
}