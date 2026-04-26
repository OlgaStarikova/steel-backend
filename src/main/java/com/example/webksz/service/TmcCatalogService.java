package com.example.webksz.service;

import com.example.webksz.dto.TmcCatalogDto;
import com.example.webksz.model.TmcCatalog;
import com.example.webksz.repository.TmcCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TmcCatalogService {

    private final TmcCatalogRepository tmcCatalogRepository;

    public List<TmcCatalogDto> findAll() {
        return tmcCatalogRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<TmcCatalogDto> findByVid(Integer vid) {
        return tmcCatalogRepository.findByVid(vid)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private TmcCatalogDto toDto(TmcCatalog tmc) {
        return new TmcCatalogDto(
                tmc.getId(),
                tmc.getVid(),
                tmc.getName(),
                tmc.getMarka(),
                tmc.getNameklass(),
                tmc.getGost(),
                tmc.getEi()
        );
    }
}