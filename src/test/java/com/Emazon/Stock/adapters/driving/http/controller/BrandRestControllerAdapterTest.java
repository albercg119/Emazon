package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandResponseMapper;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandRestControllerAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestControllerAdapter brandRestControllerAdapter;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void addBrand_ShouldReturnCreatedStatus_WhenBrandIsValid() {
        // Arrange
        AddBrandRequest request = new AddBrandRequest("Apple", "Tech company");
        Brand domainBrand = new Brand(1L, "Apple", "Tech company");

        when(brandRequestMapper.addRequestToBrand(request)).thenReturn(domainBrand);

        // Act
        ResponseEntity<Void> response = brandRestControllerAdapter.addBrand(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(brandServicePort, times(1)).saveBrand(domainBrand);
    }

    @Test
    void addBrand_ShouldReturnBadRequest_WhenRequestIsInvalid() {
        // Arrange
        AddBrandRequest invalidRequest = new AddBrandRequest("", ""); // Solicitud inválida

        // Simular que el mapeo de la solicitud a dominio también genera un error
        when(brandRequestMapper.addRequestToBrand(invalidRequest)).thenThrow(new IllegalArgumentException("Invalid Brand Request"));

        // Act
        ResponseEntity<Void> response = brandRestControllerAdapter.addBrand(invalidRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(brandServicePort, never()).saveBrand(any()); // Asegura que el servicio no se invocó
    }

    @Test
    void getAllBrands_ShouldReturnListOfBrands_WhenBrandsExist() {
        // Arrange
        Brand domainBrand1 = new Brand(1L, "Apple", "Tech company");
        Brand domainBrand2 = new Brand(2L, "Samsung", "Electronics");
        List<Brand> domainBrands = Arrays.asList(domainBrand1, domainBrand2);
        List<BrandResponse> brandResponses = Arrays.asList(
                new BrandResponse(1L, "Apple", "Tech company"),
                new BrandResponse(2L, "Samsung", "Electronics")
        );

        when(brandServicePort.getAllBrands()).thenReturn(domainBrands);
        when(brandResponseMapper.toBrandResponseList(domainBrands)).thenReturn(brandResponses);

        // Act
        ResponseEntity<List<BrandResponse>> response = brandRestControllerAdapter.getAllBrands();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponses, response.getBody());
        verify(brandServicePort, times(1)).getAllBrands();
    }

    @Test
    void getPagedBrands_ShouldReturnPagedResult_WhenBrandsExist() {
        // Arrange
        PagedResult<Brand> pagedResult = new PagedResult<>(Arrays.asList(new Brand(1L, "Apple", "Tech company")), 1, 0, 1, 1);
        PagedResult<BrandResponse> brandResponsePagedResult = new PagedResult<>(Arrays.asList(new BrandResponse(1L, "Apple", "Tech company")), 1, 0, 1, 1);

        when(brandServicePort.getPagedBrands(0, 10, true)).thenReturn(pagedResult);
        when(brandResponseMapper.toBrandResponsePagedResult(pagedResult)).thenReturn(brandResponsePagedResult);

        // Act
        ResponseEntity<PagedResult<BrandResponse>> response = brandRestControllerAdapter.getPagedBrands(0, 10, true);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponsePagedResult, response.getBody());
        verify(brandServicePort, times(1)).getPagedBrands(0, 10, true);
    }
}