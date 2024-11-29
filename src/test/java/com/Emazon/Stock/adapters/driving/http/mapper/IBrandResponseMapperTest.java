package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IBrandResponseMapperTest {

    private IBrandResponseMapper brandResponseMapper;

    @BeforeEach
    public void setUp() {
        brandResponseMapper = Mappers.getMapper(IBrandResponseMapper.class);
    }

    @Test
    public void testToBrandResponse() {
        // Arrange
        Brand brand = new Brand(1L, "Apple", "Technology and devices");

        // Act
        BrandResponse response = brandResponseMapper.toBrandResponse(brand);

        // Assert
        assertEquals(brand.getId(), response.getId());
        assertEquals(brand.getNombre(), response.getName());
        assertEquals(brand.getDescripcion(), response.getDescription());
    }

    @Test
    public void testToBrandResponseList() {
        // Arrange
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Apple", "Technology"),
                new Brand(2L, "Samsung", "Electronics")
        );

        // Act
        List<BrandResponse> responses = brandResponseMapper.toBrandResponseList(brands);

        // Assert
        assertEquals(brands.size(), responses.size());
        assertEquals(brands.get(0).getId(), responses.get(0).getId());
        assertEquals(brands.get(0).getNombre(), responses.get(0).getName());
        assertEquals(brands.get(0).getDescripcion(), responses.get(0).getDescription());
        assertEquals(brands.get(1).getId(), responses.get(1).getId());
        assertEquals(brands.get(1).getNombre(), responses.get(1).getName());
        assertEquals(brands.get(1).getDescripcion(), responses.get(1).getDescription());
    }

    @Test
    public void testToBrandResponsePagedResult() {
        // Arrange
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Apple", "Technology"),
                new Brand(2L, "Samsung", "Electronics")
        );
        int page = 0;
        int size = 10;
        long totalElements = 2L;
        int totalPages = 1;
        PagedResult<Brand> pagedResult = new PagedResult<>(brands, page, size, totalElements, totalPages);

        // Act
        PagedResult<BrandResponse> response = brandResponseMapper.toBrandResponsePagedResult(pagedResult);

        // Assert
        assertNotNull(response);
        assertEquals(pagedResult.getContent().size(), response.getContent().size());
        assertEquals(pagedResult.getPage(), response.getPage());
        assertEquals(pagedResult.getSize(), response.getSize());
        assertEquals(pagedResult.getTotalElements(), response.getTotalElements());
        assertEquals(pagedResult.getTotalPages(), response.getTotalPages());

        // Verificar el mapeo de los elementos
        for (int i = 0; i < brands.size(); i++) {
            assertEquals(brands.get(i).getId(), response.getContent().get(i).getId());
            assertEquals(brands.get(i).getNombre(), response.getContent().get(i).getName());
            assertEquals(brands.get(i).getDescripcion(), response.getContent().get(i).getDescription());
        }
    }

    @Test
    public void testToBrandResponsePagedResult_EmptyList() {
        // Arrange
        List<Brand> brands = Arrays.asList();
        PagedResult<Brand> pagedResult = new PagedResult<>(brands, 0, 10, 0L, 0);

        // Act
        PagedResult<BrandResponse> response = brandResponseMapper.toBrandResponsePagedResult(pagedResult);

        // Assert
        assertNotNull(response);
        assertTrue(response.getContent().isEmpty());
        assertEquals(pagedResult.getPage(), response.getPage());
        assertEquals(pagedResult.getSize(), response.getSize());
        assertEquals(pagedResult.getTotalElements(), response.getTotalElements());
        assertEquals(pagedResult.getTotalPages(), response.getTotalPages());
    }
}