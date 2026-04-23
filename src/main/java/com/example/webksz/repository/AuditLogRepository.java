package com.example.webksz.repository;

import com.example.webksz.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByTableNameAndRecordIdOrderByChangeTimestampDesc(String tableName, Long recordId);
}