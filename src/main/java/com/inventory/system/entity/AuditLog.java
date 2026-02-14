package com.inventory.system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action; // STOCK_IN, STOCK_OUT, ORDER_PLACED

    @Column(nullable = false)
    private Long productId;

    private String productSku;

    private Long warehouseId;

    @Column(nullable = false)
    private Integer quantityChanged;

    private String reason;

    private String userPerformed; // In a real app, this would be the username

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }
}
