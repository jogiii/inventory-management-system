package com.inventory.system.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class EntityCoverageTest {

    @Test
    void categoryEntityCoverage() {
        Category c = new Category();
        c.setId(1L);
        c.setName("C1");
        c.setDescription("D1");
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());

        assertThat(c.getId()).isEqualTo(1L);
        assertThat(c.getName()).isEqualTo("C1");
        assertThat(c.getDescription()).isEqualTo("D1");
        assertThat(c.getCreatedAt()).isNotNull();
        assertThat(c.getUpdatedAt()).isNotNull();

        Category c2 = Category.builder()
                .id(1L)
                .name("C1")
                .description("D1")
                .build();

        assertThat(c2).isNotNull();
        assertThat(c.toString()).isNotNull();
    }

    @Test
    void productEntityCoverage() {
        Category cat = new Category();
        Product p = new Product();
        p.setId(1L);
        p.setName("P1");
        p.setSku("SKU");
        p.setDescription("Desc");
        p.setPrice(BigDecimal.TEN);
        p.setLowStockThreshold(5);
        p.setCategory(cat);

        assertThat(p.getId()).isEqualTo(1L);
        assertThat(p.getName()).isEqualTo("P1");
        assertThat(p.getSku()).isEqualTo("SKU");
        assertThat(p.getDescription()).isEqualTo("Desc");
        assertThat(p.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(p.getLowStockThreshold()).isEqualTo(5);
        assertThat(p.getCategory()).isEqualTo(cat);

        Product p2 = Product.builder()
                .id(1L)
                .name("P1")
                .build();
        assertThat(p2).isNotNull();
    }

    @Test
    void stockEntityCoverage() {
        Product p = new Product();
        Warehouse w = new Warehouse();
        Stock s = new Stock();
        s.setId(1L);
        s.setProduct(p);
        s.setWarehouse(w);
        s.setQuantity(10);
        s.setReservedQuantity(2);

        assertThat(s.getId()).isEqualTo(1L);
        assertThat(s.getProduct()).isEqualTo(p);
        assertThat(s.getWarehouse()).isEqualTo(w);
        assertThat(s.getQuantity()).isEqualTo(10);
        assertThat(s.getReservedQuantity()).isEqualTo(2);
        assertThat(s.getAvailableQuantity()).isEqualTo(8);

        Stock s2 = Stock.builder()
                .id(1L)
                .quantity(10)
                .build();
        assertThat(s2.getReservedQuantity()).isEqualTo(0);
    }

    @Test
    void warehouseEntityCoverage() {
        Warehouse w = new Warehouse();
        w.setId(1L);
        w.setName("W1");
        w.setLocation("Loc");

        assertThat(w.getId()).isEqualTo(1L);
        assertThat(w.getName()).isEqualTo("W1");
        assertThat(w.getLocation()).isEqualTo("Loc");

        Warehouse w2 = Warehouse.builder().id(1L).build();
        assertThat(w2).isNotNull();
    }

    @Test
    void auditLogEntityCoverage() {
        AuditLog log = new AuditLog();
        log.setId(1L);
        log.setAction("ACT");
        log.setProductId(1L);
        log.setProductSku("SKU");
        log.setWarehouseId(1L);
        log.setQuantityChanged(5);
        log.setReason("R");
        log.setUserPerformed("U");
        log.setTimestamp(LocalDateTime.now());

        assertThat(log.getId()).isEqualTo(1L);
        assertThat(log.getAction()).isEqualTo("ACT");
        assertThat(log.getProductId()).isEqualTo(1L);

        AuditLog log2 = AuditLog.builder().id(1L).build();
        assertThat(log2).isNotNull();
    }

    @Test
    void baseEntityCoverage() {
        Category c = new Category();
        c.setCreatedAt(LocalDateTime.now());
        c.setUpdatedAt(LocalDateTime.now());

        assertThat(c.getCreatedAt()).isNotNull();
        assertThat(c.getUpdatedAt()).isNotNull();

        Category c2 = Category.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        assertThat(c2.getCreatedAt()).isNotNull();
    }
}
