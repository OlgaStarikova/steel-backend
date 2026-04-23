package com.example.webksz.repository;

import com.example.webksz.model.TmcCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TmcCatalogRepository extends JpaRepository<TmcCatalog, Long> {
    List<TmcCatalog> findByVid(Integer vid);
}