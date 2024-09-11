package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.model.Brand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandRestControllerAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

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
        AddBrandRequest invalidRequest = new AddBrandRequest("", "");

        // Act
        ResponseEntity<Void> response = brandRestControllerAdapter.addBrand(invalidRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(brandServicePort, never()).saveBrand(any());
    }
}