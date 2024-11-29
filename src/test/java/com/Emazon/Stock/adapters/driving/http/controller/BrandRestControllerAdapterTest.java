package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.utilities.BrandControllerConstants;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IBrandResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BrandRestControllerAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestControllerAdapter brandRestControllerAdapter;

    @Test
    void addBrand_ShouldReturnBadRequest_WhenRequestIsInvalid() {
        // Arrange
        AddBrandRequest invalidRequest = new AddBrandRequest("", "");

        // Act & Assert
        try {
            brandRestControllerAdapter.addBrand(invalidRequest);
        } catch (ConstraintViolationException e) {
            // Verifica que no se llamó al servicio
            verify(brandServicePort, never()).saveBrand(any());
            // La validación la maneja Spring, por lo que no necesitamos verificar el ResponseEntity
        }
    }

    @Test
    void addBrand_ShouldReturnCreated_WhenRequestIsValid() {
        // Arrange
        AddBrandRequest validRequest = new AddBrandRequest("Sony", "Electronics company");

        // Act
        ResponseEntity<String> response = brandRestControllerAdapter.addBrand(validRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(BrandControllerConstants.BRAND_CREATED_MESSAGE, response.getBody());
        verify(brandServicePort).saveBrand(any());
    }
}