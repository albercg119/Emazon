package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ICategoryRequestMapperTest {

    private ICategoryRequestMapper categoryRequestMapper;

    @BeforeEach
    public void setUp() {
        // Obtiene una instancia del mapper usando Mappers.getMapper
        categoryRequestMapper = Mappers.getMapper(ICategoryRequestMapper.class);
    }

    @Test
    public void testAddRequestToCategory() {
        // Arrange
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("Electronics", "Devices and gadgets");

        // Act
        Category category = categoryRequestMapper.addRequestToCategory(addCategoryRequest);

        // Assert
        assertEquals(null, category.getId(), "ID should be null as it is ignored");
        assertEquals("Electronics", category.getNombre(), "Name should be mapped correctly");
        assertEquals("Devices and gadgets", category.getDescripcion(), "Description should be mapped correctly");
    }
}