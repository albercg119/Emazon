package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandResponseMapper;
import com.Emazon.Stock.adapters.utilities.BrandControllerConstants;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BrandRestControllerAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestControllerAdapter brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBrand_ShouldReturnCreatedStatus() {
        // Arrange
        AddBrandRequest request = new AddBrandRequest("Nike", "Deportes");
        Brand brand = new Brand(1L, "Nike", "Deportes");
        when(brandRequestMapper.addRequestToBrand(any(AddBrandRequest.class))).thenReturn(brand);

        // Act
        ResponseEntity<String> response = brandController.addBrand(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(BrandControllerConstants.BRAND_CREATED_SUCCESSFULLY, response.getBody());
        verify(brandServicePort).saveBrand(brand);
    }

    @Test
    void getPagedBrands_ShouldReturnPagedResult() {
        // Arrange
        List<Brand> brandList = Arrays.asList(
                new Brand(1L, "Nike", "Deportes"),
                new Brand(2L, "Adidas", "Deportes")
        );
        PagedResult<Brand> brandPagedResult = new PagedResult<>(brandList, 0, 10, 2L, 1);

        List<BrandResponse> responseList = Arrays.asList(
                new BrandResponse(1L, "Nike", "Deportes"),
                new BrandResponse(2L, "Adidas", "Deportes")
        );
        PagedResult<BrandResponse> expectedResponse = new PagedResult<>(responseList, 0, 10, 2L, 1);

        when(brandServicePort.getPagedBrands(anyInt(), anyInt(), anyBoolean()))
                .thenReturn(brandPagedResult);
        when(brandResponseMapper.toBrandResponsePagedResult(any()))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<PagedResult<BrandResponse>> response =
                brandController.getPagedBrands(0, 10, "asc");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(brandServicePort).getPagedBrands(0, 10, true);
    }

    @Test
    void getPagedBrands_WithDescendingSort_ShouldReturnPagedResult() {
        // Arrange
        List<Brand> brandList = Arrays.asList(
                new Brand(1L, "Nike", "Deportes"),
                new Brand(2L, "Adidas", "Deportes")
        );
        PagedResult<Brand> brandPagedResult = new PagedResult<>(brandList, 0, 10, 2L, 1);

        List<BrandResponse> responseList = Arrays.asList(
                new BrandResponse(1L, "Nike", "Deportes"),
                new BrandResponse(2L, "Adidas", "Deportes")
        );
        PagedResult<BrandResponse> expectedResponse = new PagedResult<>(responseList, 0, 10, 2L, 1);

        when(brandServicePort.getPagedBrands(anyInt(), anyInt(), anyBoolean()))
                .thenReturn(brandPagedResult);
        when(brandResponseMapper.toBrandResponsePagedResult(any()))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<PagedResult<BrandResponse>> response =
                brandController.getPagedBrands(0, 10, "desc");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(brandServicePort).getPagedBrands(0, 10, false);
    }

    @Test
    void getAllBrands_ShouldReturnListOfBrands() {
        // Arrange
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Nike", "Deportes"),
                new Brand(2L, "Adidas", "Deportes")
        );
        List<BrandResponse> expectedResponses = Arrays.asList(
                new BrandResponse(1L, "Nike", "Deportes"),
                new BrandResponse(2L, "Adidas", "Deportes")
        );
        when(brandServicePort.getAllBrands()).thenReturn(brands);
        when(brandResponseMapper.toBrandResponseList(brands)).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<BrandResponse>> response = brandController.getAllBrands();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponses, response.getBody());
        verify(brandServicePort).getAllBrands();
        verify(brandResponseMapper).toBrandResponseList(brands);
    }
}