package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class IBrandRequestMapperTest {

    private IBrandRequestMapper brandRequestMapper;

    @BeforeEach
    public void setUp() {
        brandRequestMapper = Mappers.getMapper(IBrandRequestMapper.class);
    }

    @Test
    public void testAddRequestToBrand() {
        // Arrange
        String name = "Apple";
        String description = "Technology and devices";
        AddBrandRequest request = new AddBrandRequest(name, description);

        // Act
        Brand brand = brandRequestMapper.addRequestToBrand(request);

        // Assert
        assertNull(brand.getId(), "ID should be null as it is ignored in mapping");
        assertEquals(name, brand.getNombre(), "Name should be mapped to nombre");
        assertEquals(description, brand.getDescripcion(), "Description should be mapped to descripcion");
    }

    @Test
    public void testAddRequestToBrandWithNullValues() {
        // Arrange
        AddBrandRequest request = new AddBrandRequest(null, null);

        // Act
        Brand brand = brandRequestMapper.addRequestToBrand(request);

        // Assert
        assertNull(brand.getId());
        assertNull(brand.getNombre());
        assertNull(brand.getDescripcion());
    }
}