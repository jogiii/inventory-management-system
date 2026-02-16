package com.inventory.system.mapper;

import com.inventory.system.dto.CategoryDTO;
import com.inventory.system.dto.ProductDTO;
import com.inventory.system.dto.StockDTO;
import com.inventory.system.entity.Category;
import com.inventory.system.entity.Product;
import com.inventory.system.entity.Stock;
import com.inventory.system.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MapperCoverageTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StockMapper stockMapper;

    @Test
    void categoryMapper_Coverage() {
        assertThat(categoryMapper.toDTO(null)).isNull();
        assertThat(categoryMapper.toEntity(null)).isNull();

        Category category = Category.builder().id(1L).name("C1").build();
        CategoryDTO dto = categoryMapper.toDTO(category);
        assertThat(dto.getName()).isEqualTo("C1");

        Category entity = categoryMapper.toEntity(dto);
        assertThat(entity.getName()).isEqualTo("C1");
    }

    @Test
    void productMapper_Coverage() {
        assertThat(productMapper.toDTO(null)).isNull();
        assertThat(productMapper.toEntity(null)).isNull();

        Product product = Product.builder().id(1L).name("P1").price(BigDecimal.TEN).build();
        ProductDTO dto = productMapper.toDTO(product);
        assertThat(dto.getName()).isEqualTo("P1");

        Product entity = productMapper.toEntity(dto);
        assertThat(entity.getName()).isEqualTo("P1");
    }

    @Test
    void stockMapper_Coverage() {
        assertThat(stockMapper.toDTO(null)).isNull();

        Product product = Product.builder().id(1L).sku("S1").build();
        Warehouse warehouse = Warehouse.builder().id(2L).build();
        Stock stock = Stock.builder()
                .id(1L)
                .product(product)
                .warehouse(warehouse)
                .quantity(10)
                .build();

        StockDTO dto = stockMapper.toDTO(stock);
        assertThat(dto.getProductId()).isEqualTo(1L);
        assertThat(dto.getProductSku()).isEqualTo("S1");
        assertThat(dto.getWarehouseId()).isEqualTo(2L);

        // Test with null references in stock (but need quantity to avoid NPE in
        // getAvailableQuantity)
        Stock stockNulls = new Stock();
        stockNulls.setQuantity(0);
        stockNulls.setReservedQuantity(0);
        StockDTO dtoNulls = stockMapper.toDTO(stockNulls);
        assertThat(dtoNulls).isNotNull();
        assertThat(dtoNulls.getProductId()).isNull();
    }
}
