package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.CategoryAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.adapters.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory_WhenCategoryDoesNotExist_ShouldSaveSuccessfully() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setNombre("Electronics");
        categoryEntity.setDescripcion("Devices");

        when(categoryRepository.findByNombre(category.getNombre())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void testSaveCategory_WhenCategoryExists_ShouldThrowCategoryAlreadyExistsException() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices");
        when(categoryRepository.findByNombre(category.getNombre())).thenReturn(Optional.of(new CategoryEntity()));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryAdapter.saveCategory(category);
        });

        verify(categoryRepository, never()).save(any(CategoryEntity.class));
    }

    @Test
    void testExistsByName_WhenCategoryExists_ShouldReturnTrue() {
        // Arrange
        String categoryName = "Electronics";
        when(categoryRepository.findByNombre(categoryName)).thenReturn(Optional.of(new CategoryEntity()));

        // Act
        boolean result = categoryAdapter.existsByName(categoryName);

        // Assert
        assertTrue(result);
        verify(categoryRepository, times(1)).findByNombre(categoryName);
    }

    @Test
    void testExistsByName_WhenCategoryDoesNotExist_ShouldReturnFalse() {
        // Arrange
        String categoryName = "Electronics";
        when(categoryRepository.findByNombre(categoryName)).thenReturn(Optional.empty());

        // Act
        boolean result = categoryAdapter.existsByName(categoryName);

        // Assert
        assertFalse(result);
        verify(categoryRepository, times(1)).findByNombre(categoryName);
    }

}