package com.example.webksz.mapper;

import com.example.webksz.config.MapperConfig;
import com.example.webksz.dto.CreateSteelGradeRequestDto;
import com.example.webksz.dto.SteelGradeDto;
import com.example.webksz.model.SteelGrade;
import com.example.webksz.model.SteelGradesGroup;
import com.example.webksz.repository.SteelGradesGroupRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class)
public interface SteelGradeMapper {

    @Mapping(source = "group.name", target = "groupName")
    SteelGradeDto toDto(SteelGrade steelGrade);

    default SteelGradeDto toDto(SteelGrade steelGrade, Boolean hasRecipe) {
        SteelGradeDto dto = toDto(steelGrade);
        return new SteelGradeDto(dto.id(), dto.name(), dto.groupName(), hasRecipe);
    }

    @Mapping(source = "groupId", target = "group")
    @Mapping(target = "id", ignore = true)
    SteelGrade toModel(CreateSteelGradeRequestDto requestDto);

    default SteelGradesGroup mapGroup(Long groupId) {
        if (groupId == null) {
            return null;
        }
        SteelGradesGroup group = new SteelGradesGroup();
        group.setId(groupId);
        return group;
    }
}