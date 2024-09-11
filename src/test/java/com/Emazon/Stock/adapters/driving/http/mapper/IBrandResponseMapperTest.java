package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        BrandResponse brandResponse = brandResponseMapper.toBrandResponse(brand);

        // Assert
        assertNotNull(brandResponse, "BrandResponse should not be null");
        assertEquals(brand.getId(), brandResponse.getId(), "ID should be mapped correctly");
        assertEquals(brand.getNombre(), brandResponse.getName(), "Name should be mapped correctly");
        assertEquals(brand.getDescripcion(), brandResponse.getDescription(), "Description should be mapped correctly");
    }

    @Test
    public void testToBrandResponseList() {
        // Arrange
        Brand brand1 = new Brand(1L, "Apple", "Technology and devices");
        Brand brand2 = new Brand(2L, "Samsung", "Electronics and home appliances");
        List<Brand> brands = Arrays.asList(brand1, brand2);

        // Act
        List<BrandResponse> brandResponses = brandResponseMapper.toBrandResponseList(brands);

        // Assert
        assertNotNull(brandResponses, "BrandResponse list should not be null");
        assertEquals(2, brandResponses.size(), "The size of the list should match");
        assertEquals(brand1.getId(), brandResponses.get(0).getId(), "ID should be mapped correctly");
        assertEquals(brand2.getNombre(), brandResponses.get(1).getName(), "Name should be mapped correctly");
    }

    @Test
    public void testToBrandResponsePagedResult() {
        // Arrange
        Brand brand1 = new Brand(1L, "Nike", "Sportswear and equipment");
        Brand brand2 = new Brand(2L, "Adidas", "Footwear and sports apparel");
        List<Brand> brands = Arrays.asList(brand1, brand2);
        PagedResult<Brand> pagedResult = new PagedResult<>(brands, 1, 2, 2, 1);

        // Act
        PagedResult<BrandResponse> brandResponsePagedResult = brandResponseMapper.toBrandResponsePagedResult(pagedResult);

        // Assert
        assertNotNull(brandResponsePagedResult, "PagedResult<BrandResponse> should not be null");
        assertEquals(pagedResult.getTotalElements(), brandResponsePagedResult.getTotalElements(), "Total elements should be mapped correctly");
        assertEquals(pagedResult.getTotalPages(), brandResponsePagedResult.getTotalPages(), "Total pages should be mapped correctly");
        assertEquals(2, brandResponsePagedResult.getContent().size(), "The size of the content list should match");
    }
}