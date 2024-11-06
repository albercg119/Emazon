package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.adapters.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    void saveCategory_ShouldSaveSuccessfully_WhenCategoryDoesNotExist() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Electronic devices");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryRepository.findByNombre(category.getNombre())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryExists() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Electronic devices");
        when(categoryRepository.findByNombre(category.getNombre())).thenReturn(Optional.of(new CategoryEntity()));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryAdapter.saveCategory(category));
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void getPagedCategories_ShouldReturnPagedResult_WhenCategoriesExist() {
        // Arrange
        int page = 0;
        int size = 10;
        List<CategoryEntity> categoryEntities = Arrays.asList(
                new CategoryEntity(), new CategoryEntity()
        );
        Page<CategoryEntity> categoryPage = new PageImpl<>(categoryEntities);
        List<Category> categories = Arrays.asList(
                new Category(1L, "Electronics", "Devices"),
                new Category(2L, "Books", "Reading materials")
        );

        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(categoryPage);
        when(categoryEntityMapper.toModelList(categoryEntities)).thenReturn(categories);

        // Act
        PagedResult<Category> result = categoryAdapter.getPagedCategories(page, size, true);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(categoryRepository).findAll(any(PageRequest.class));
    }

    @Test
    void getPagedCategories_ShouldThrowException_WhenNoCategoriesExist() {
        // Arrange
        when(categoryRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Act & Assert
        assertThrows(NoDataFoundException.class,
                () -> categoryAdapter.getPagedCategories(0, 10, true));
    }

    @Test
    void getAllCategories_ShouldReturnList_WhenCategoriesExist() {
        // Arrange
        List<CategoryEntity> categoryEntities = Arrays.asList(new CategoryEntity(), new CategoryEntity());
        List<Category> expectedCategories = Arrays.asList(
                new Category(1L, "Electronics", "Devices"),
                new Category(2L, "Books", "Reading materials")
        );

        when(categoryRepository.findAll()).thenReturn(categoryEntities);
        when(categoryEntityMapper.toModelList(categoryEntities)).thenReturn(expectedCategories);

        // Act
        List<Category> result = categoryAdapter.getAllCategories();

        // Assert
        assertEquals(expectedCategories.size(), result.size());
        verify(categoryRepository).findAll();
    }

    @Test
    void getAllCategories_ShouldThrowException_WhenNoCategoriesExist() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> categoryAdapter.getAllCategories());
    }

    @Test
    void existsByName_ShouldReturnTrue_WhenCategoryExists() {
        // Arrange
        String name = "Electronics";
        when(categoryRepository.findByNombre(name)).thenReturn(Optional.of(new CategoryEntity()));

        // Act
        boolean result = categoryAdapter.existsByName(name);

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByName_ShouldReturnFalse_WhenCategoryDoesNotExist() {
        // Arrange
        String name = "NonexistentCategory";
        when(categoryRepository.findByNombre(name)).thenReturn(Optional.empty());

        // Act
        boolean result = categoryAdapter.existsByName(name);

        // Assert
        assertFalse(result);
    }

    @Test
    void constructor_ShouldInitializeCorrectly() {
        // Arrange & Act
        CategoryAdapter adapter = new CategoryAdapter(categoryRepository, categoryEntityMapper);

        // Assert
        assertNotNull(adapter);
    }
}