package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.model.Category;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryRestControllerAdapterTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

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
    void addCategory_ShouldReturnCreatedStatus_WhenCategoryIsValid() {
        // Arrange
        AddCategoryRequest request = new AddCategoryRequest("Electronics", "Devices and gadgets");
        Category domainCategory = new Category(1L, "Electronics", "Devices and gadgets");

        when(categoryRequestMapper.addRequestToCategory(request)).thenReturn(domainCategory);

        // Act
        ResponseEntity<Void> response = categoryRestControllerAdapter.addCategory(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoryServicePort, times(1)).saveCategory(domainCategory);
    }

    @Test
    void getPagedCategories_ShouldReturnPagedCategories_WhenCategoriesAreAvailable() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;

        Category category1 = new Category(1L, "Electronics", "Devices");
        Category category2 = new Category(2L, "Books", "Various books");

        List<Category> categories = Arrays.asList(category1, category2);
        PagedResult<Category> pagedResult = new PagedResult<>(categories, page, size, 2, 1);

        CategoryResponse categoryResponse1 = new CategoryResponse(1L, "Electronics", "Devices");
        CategoryResponse categoryResponse2 = new CategoryResponse(2L, "Books", "Various books");

        List<CategoryResponse> categoryResponses = Arrays.asList(categoryResponse1, categoryResponse2);
        PagedResult<CategoryResponse> pagedResultResponse = new PagedResult<>(categoryResponses, page, size, 2, 1);

        when(categoryServicePort.getPagedCategories(page, size, ascending)).thenReturn(pagedResult);
        when(categoryResponseMapper.toCategoryResponsePagedResult(pagedResult)).thenReturn(pagedResultResponse);

        // Act
        ResponseEntity<PagedResult<CategoryResponse>> responseEntity = categoryRestControllerAdapter.getPagedCategories(page, size, ascending);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pagedResultResponse, responseEntity.getBody());
        verify(categoryServicePort, times(1)).getPagedCategories(page, size, ascending);
        verify(categoryResponseMapper, times(1)).toCategoryResponsePagedResult(pagedResult);
    }

    @Test
    void getPagedCategories_ShouldReturnEmptyPagedResult_WhenNoCategoriesAvailable() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;

        PagedResult<Category> pagedResult = new PagedResult<>(Collections.emptyList(), page, size, 0, 0);
        PagedResult<CategoryResponse> pagedResultResponse = new PagedResult<>(Collections.emptyList(), page, size, 0, 0);

        when(categoryServicePort.getPagedCategories(page, size, ascending)).thenReturn(pagedResult);
        when(categoryResponseMapper.toCategoryResponsePagedResult(pagedResult)).thenReturn(pagedResultResponse);

        // Act
        ResponseEntity<PagedResult<CategoryResponse>> responseEntity = categoryRestControllerAdapter.getPagedCategories(page, size, ascending);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pagedResultResponse, responseEntity.getBody());
        verify(categoryServicePort, times(1)).getPagedCategories(page, size, ascending);
        verify(categoryResponseMapper, times(1)).toCategoryResponsePagedResult(pagedResult);
    }

    @Test
    void getAllCategories_ShouldReturnAllCategories_WhenCategoriesAreAvailable() {
        // Arrange
        Category category1 = new Category(1L, "Electronics", "Devices");
        Category category2 = new Category(2L, "Books", "Various books");

        List<Category> categories = Arrays.asList(category1, category2);

        CategoryResponse categoryResponse1 = new CategoryResponse(1L, "Electronics", "Devices");
        CategoryResponse categoryResponse2 = new CategoryResponse(2L, "Books", "Various books");

        List<CategoryResponse> categoryResponses = Arrays.asList(categoryResponse1, categoryResponse2);

        when(categoryServicePort.getAllCategories()).thenReturn(categories);
        when(categoryResponseMapper.toCategoryResponseList(categories)).thenReturn(categoryResponses);

        // Act
        ResponseEntity<List<CategoryResponse>> responseEntity = categoryRestControllerAdapter.getAllCategories();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categoryResponses, responseEntity.getBody());
        verify(categoryServicePort, times(1)).getAllCategories();
        verify(categoryResponseMapper, times(1)).toCategoryResponseList(categories);
    }

    @Test
    void getAllCategories_ShouldReturnNoContent_WhenNoCategoriesAvailable() {
        // Arrange
        when(categoryServicePort.getAllCategories()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<CategoryResponse>> responseEntity = categoryRestControllerAdapter.getAllCategories();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
        verify(categoryServicePort, times(1)).getAllCategories();
        verify(categoryResponseMapper, times(1)).toCategoryResponseList(Collections.emptyList());
    }
}