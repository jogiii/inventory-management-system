package com.inventory.system.mapper;

import com.inventory.system.dto.ProductDTO;
import com.inventory.system.entity.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toDTO_ShouldMapFields() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Pro");
        product.setPrice(BigDecimal.ONE);

        ProductDTO dto = mapper.toDTO(product);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Pro");
        assertThat(dto.getPrice()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void toEntity_ShouldMapFields() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("Pro");

        Product product = mapper.toEntity(dto);

        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Pro");
    }
}
