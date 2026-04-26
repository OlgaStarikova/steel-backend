package com.example.webksz.controller;

import com.example.webksz.dto.TmcCatalogDto;
import com.example.webksz.service.TmcCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/webksz/tmc-catalog")
public class TmcCatalogController {

    @Autowired
    private TmcCatalogService tmcCatalogService;

    @GetMapping
    public ResponseEntity<List<TmcCatalogDto>> getAll() {
        return ResponseEntity.ok(tmcCatalogService.findAll());
    }

    @GetMapping("/vid/{vid}")
    public ResponseEntity<List<TmcCatalogDto>> getByVid(@PathVariable Integer vid) {
        return ResponseEntity.ok(tmcCatalogService.findByVid(vid));
    }
}