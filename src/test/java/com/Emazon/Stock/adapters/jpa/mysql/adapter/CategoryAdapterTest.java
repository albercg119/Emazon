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
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
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



    @Test
    void testGetPagedCategories() {
        // Datos simulados para la paginación
        int page = 0;
        int size = 10;
        boolean ascending = true;

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(1L);
        categoryEntity1.setNombre("Electrónica");
        categoryEntity1.setDescripcion("Categoría de productos electrónicos");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2L);
        categoryEntity2.setNombre("Ropa");
        categoryEntity2.setDescripcion("Categoría de ropa");

        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntity1, categoryEntity2);

        Page<CategoryEntity> categoryPage = mock(Page.class);
        when(categoryPage.getContent()).thenReturn(categoryEntityList);
        when(categoryPage.getNumber()).thenReturn(page);
        when(categoryPage.getSize()).thenReturn(size);
        when(categoryPage.getTotalElements()).thenReturn((long) categoryEntityList.size());
        when(categoryPage.getTotalPages()).thenReturn(1);
        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(categoryPage);

        // Simular el mapeo de CategoryEntity a Category
        when(categoryEntityMapper.toModelList(categoryEntityList)).thenReturn(Arrays.asList(
                new Category(1L, "Electrónica", "Categoría de productos electrónicos"),
                new Category(2L, "Ropa", "Categoría de ropa")
        ));

        // Ejecutar el método
        PagedResult<Category> pagedResult = categoryAdapter.getPagedCategories(page, size, ascending);

        // Validar el resultado
        assertEquals(page, pagedResult.getPage());
        assertEquals(size, pagedResult.getSize());
        assertEquals(categoryEntityList.size(), pagedResult.getTotalElements());
        assertEquals(1, pagedResult.getTotalPages());
        verify(categoryRepository, times(1)).findAll(any(PageRequest.class));
        verify(categoryEntityMapper, times(1)).toModelList(categoryEntityList);
    }

    @Test
    void testGetPagedCategoriesThrowsNoDataFoundException() {
        // Datos simulados para la paginación
        int page = 0;
        int size = 10;
        boolean ascending = true;

        Page<CategoryEntity> emptyCategoryPage = mock(Page.class);
        when(emptyCategoryPage.isEmpty()).thenReturn(true);
        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(emptyCategoryPage);

        // Ejecutar el método y verificar la excepción
        assertThrows(NoDataFoundException.class, () -> categoryAdapter.getPagedCategories(page, size, ascending));
    }

    @Test
    void testGetAllCategories() {
        // Datos simulados
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(1L);
        categoryEntity1.setNombre("Electrónica");
        categoryEntity1.setDescripcion("Categoría de productos electrónicos");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2L);
        categoryEntity2.setNombre("Ropa");
        categoryEntity2.setDescripcion("Categoría de ropa");

        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntity1, categoryEntity2);

        // Simular el comportamiento del puerto de persistencia
        when(categoryRepository.findAll()).thenReturn(categoryEntityList);
        when(categoryEntityMapper.toModelList(categoryEntityList)).thenReturn(Arrays.asList(
                new Category(1L, "Electrónica", "Categoría de productos electrónicos"),
                new Category(2L, "Ropa", "Categoría de ropa")
        ));

        // Ejecutar el método
        List<Category> categories = categoryAdapter.getAllCategories();

        // Validar el resultado
        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
        verify(categoryEntityMapper, times(1)).toModelList(categoryEntityList);
    }

    @Test
    void testGetAllCategoriesThrowsNoDataFoundException() {
        // Simular que no hay categorías
        when(categoryRepository.findAll()).thenReturn(Arrays.asList());

        // Ejecutar el método y verificar la excepción
        assertThrows(NoDataFoundException.class, () -> categoryAdapter.getAllCategories());
    }
}