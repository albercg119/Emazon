package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ICategoryEntityMapperTest {

    private ICategoryEntityMapper categoryEntityMapper;

    @BeforeEach
    void setUp() {
        // Usa la implementación generada por MapStruct
        categoryEntityMapper = Mappers.getMapper(ICategoryEntityMapper.class);
    }

    @Test
    void testToModel() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setNombre("Electronics");
        categoryEntity.setDescripcion("Devices and gadgets");

        // Act
        Category category = categoryEntityMapper.toModel(categoryEntity);

        // Assert
        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getNombre());
        assertEquals("Devices and gadgets", category.getDescripcion());
    }

    @Test
    void testToEntity() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices and gadgets");

        // Act
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);

        // Assert
        assertNotNull(categoryEntity);
        assertEquals(1L, categoryEntity.getId());
        assertEquals("Electronics", categoryEntity.getNombre());
        assertEquals("Devices and gadgets", categoryEntity.getDescripcion());
    }

    @Test
    void testToModelList() {
        // Arrange
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(1L);
        categoryEntity1.setNombre("Electronics");
        categoryEntity1.setDescripcion("Devices");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2L);
        categoryEntity2.setNombre("Furniture");
        categoryEntity2.setDescripcion("Home and office furniture");

        List<CategoryEntity> categoryEntities = Arrays.asList(categoryEntity1, categoryEntity2);

        // Act
        List<Category> categories = categoryEntityMapper.toModelList(categoryEntities);

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());

        Category category1 = categories.get(0);
        assertEquals(1L, category1.getId());
        assertEquals("Electronics", category1.getNombre());
        assertEquals("Devices", category1.getDescripcion());

        Category category2 = categories.get(1);
        assertEquals(2L, category2.getId());
        assertEquals("Furniture", category2.getNombre());
        assertEquals("Home and office furniture", category2.getDescripcion());
    }
}