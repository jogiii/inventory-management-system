package com.inventory.system.mapper;

import com.inventory.system.dto.CategoryDTO;
import com.inventory.system.entity.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void toDTO_ShouldMapFields() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Cat");

        CategoryDTO dto = mapper.toDTO(category);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Cat");
    }

    @Test
    void toEntity_ShouldMapFields() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("Cat");

        Category category = mapper.toEntity(dto);

        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("Cat");
    }
}
