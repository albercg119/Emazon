package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.model.Category; // Ajusta la importación según corresponda
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryRestControllerAdapterTest implements AutoCloseable {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); // Usamos un AutoCloseable
    }

    @Test
    void addCategory_ShouldReturnCreatedStatus_WhenCategoryIsValid() {
        // Arrange
        AddCategoryRequest request = new AddCategoryRequest("Electronics", "Devices and gadgets");

        // Asumimos que el constructor de Category necesita un id (Long), nombre y descripción
        Category domainCategory = new Category(1L, "Electronics", "Devices and gadgets");

        // Mocks
        when(categoryRequestMapper.addRequestToCategory(request)).thenReturn(domainCategory);

        // Act
        ResponseEntity<Void> response = categoryRestControllerAdapter.addCategory(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoryServicePort, times(1)).saveCategory(domainCategory);
    }

    @Override
    public void close() throws Exception {
        closeable.close(); // Cerrar los mocks en el método close
    }
}