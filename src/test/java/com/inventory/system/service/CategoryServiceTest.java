package com.inventory.system.service;

import com.inventory.system.dto.CategoryDTO;
import com.inventory.system.entity.Category;
import com.inventory.system.mapper.CategoryMapper;
import com.inventory.system.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void createCategory_ShouldReturnDTO_WhenSuccessful() {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("Test Category");

        Category category = new Category();
        category.setName("Test Category");

        when(categoryMapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryMapper.toDTO(category)).thenReturn(dto);

        CategoryDTO result = categoryService.createCategory(dto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Category");
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void getAllCategories_ShouldReturnList_WhenNotEmpty() {
        Category category = new Category();
        CategoryDTO dto = new CategoryDTO();

        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        when(categoryMapper.toDTO(category)).thenReturn(dto);

        List<CategoryDTO> result = categoryService.getAllCategories();

        assertThat(result).hasSize(1);
        verify(categoryRepository, times(1)).findAll();
    }
}
