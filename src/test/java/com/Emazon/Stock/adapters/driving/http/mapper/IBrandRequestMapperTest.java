package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IBrandRequestMapperTest {

    private IBrandRequestMapper brandRequestMapper;

    @BeforeEach
    public void setUp() {
        // Obtiene una instancia del mapper usando Mappers.getMapper
        brandRequestMapper = Mappers.getMapper(IBrandRequestMapper.class);
    }

    @Test
    public void testAddRequestToBrand() {
        // Arrange
        AddBrandRequest addBrandRequest = new AddBrandRequest("Apple", "Technology and devices");

        // Act
        Brand brand = brandRequestMapper.addRequestToBrand(addBrandRequest);

        // Assert
        assertEquals(null, brand.getId(), "ID should be null as it is ignored");
        assertEquals("Apple", brand.getNombre(), "Name should be mapped correctly");
        assertEquals("Technology and devices", brand.getDescripcion(), "Description should be mapped correctly");
    }
}