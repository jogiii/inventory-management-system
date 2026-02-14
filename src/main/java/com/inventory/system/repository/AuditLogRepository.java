package com.inventory.system.repository;

import com.inventory.system.entity.AuditLog;
import com.inventory.system.entity.Category;
import com.inventory.system.entity.Order;
import com.inventory.system.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
