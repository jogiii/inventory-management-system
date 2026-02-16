package com.inventory.system;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.Category;
import com.inventory.system.entity.Product;
import com.inventory.system.entity.Stock;
import com.inventory.system.entity.Warehouse;
import com.inventory.system.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InventoryH2IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Long productId;
    private Long warehouseId;

    @BeforeEach
    void setUp() {
        stockRepository.deleteAll();
        auditLogRepository.deleteAll();
        productRepository.deleteAll();
        warehouseRepository.deleteAll();
        categoryRepository.deleteAll();

        Category cat = categoryRepository
                .save(Category.builder().name("Electronics").updatedAt(LocalDateTime.now()).build());

        Product p = Product.builder()
                .name("Laptop")
                .sku("LAP-001")
                .price(new BigDecimal("1000.00"))
                .lowStockThreshold(5)
                .category(cat)
                .updatedAt(LocalDateTime.now())
                .build();
        productId = productRepository.save(p).getId();

        Warehouse w = Warehouse.builder().name("Main Warehouse").location("NYC").updatedAt(LocalDateTime.now()).build();
        warehouseId = warehouseRepository.save(w).getId();
    }

    @Test
    void testStockInFlow() throws Exception {
        StockOperationDTO op = new StockOperationDTO();
        op.setProductId(productId);
        op.setWarehouseId(warehouseId);
        op.setQuantity(10);
        op.setType(StockOperationDTO.OperationType.IN);
        op.setReason("Initial Stock");

        mockMvc.perform(post("/api/inventory/operate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(op)))
                .andExpect(status().isOk());

        // Verify Stock
        Stock stock = stockRepository.findByProductIdAndWarehouseId(productId, warehouseId).orElseThrow();
        assertThat(stock.getQuantity()).isEqualTo(10);

        // Verify Audit
        assertThat(auditLogRepository.count()).isEqualTo(1);
    }
}
