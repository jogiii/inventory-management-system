package com.inventory.system.mapper;

import com.inventory.system.dto.CategoryDTO;
import com.inventory.system.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO dto);
}
