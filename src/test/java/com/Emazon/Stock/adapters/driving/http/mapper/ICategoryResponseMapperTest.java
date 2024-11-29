package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ICategoryResponseMapperTest {

    private ICategoryResponseMapper categoryResponseMapper;

    @BeforeEach
    public void setUp() {
        categoryResponseMapper = Mappers.getMapper(ICategoryResponseMapper.class);
    }

    @Test
    public void testToCategoryResponse() {
        // Arrange
        Category category = new Category(1L, "Electronics", "Devices and gadgets");

        // Act
        CategoryResponse categoryResponse = categoryResponseMapper.toCategoryResponse(category);

        // Assert
        assertNotNull(categoryResponse, "CategoryResponse should not be null");
        assertEquals(category.getId(), categoryResponse.getId(), "ID should be mapped correctly");
        assertEquals(category.getNombre(), categoryResponse.getName(), "Name should be mapped correctly");
        assertEquals(category.getDescripcion(), categoryResponse.getDescription(), "Description should be mapped correctly");
    }

    @Test
    public void testToCategoryResponseList() {
        // Arrange
        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Various books");
        List<Category> categories = Arrays.asList(category1, category2);

        // Act
        List<CategoryResponse> categoryResponses = categoryResponseMapper.toCategoryResponseList(categories);

        // Assert
        assertNotNull(categoryResponses, "CategoryResponse list should not be null");
        assertEquals(2, categoryResponses.size(), "The size of the list should match");
        assertEquals(category1.getId(), categoryResponses.get(0).getId(), "ID should be mapped correctly");
        assertEquals(category2.getNombre(), categoryResponses.get(1).getName(), "Name should be mapped correctly");
    }

    @Test
    public void testToCategoryResponsePagedResult() {
        // Arrange
        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Various books");
        List<Category> categories = Arrays.asList(category1, category2);
        PagedResult<Category> pagedResult = new PagedResult<>(categories, 1, 2, 2, 1);

        // Act
        PagedResult<CategoryResponse> categoryResponsePagedResult = categoryResponseMapper.toCategoryResponsePagedResult(pagedResult);

        // Assert
        assertNotNull(categoryResponsePagedResult, "PagedResult<CategoryResponse> should not be null");
        assertEquals(pagedResult.getTotalElements(), categoryResponsePagedResult.getTotalElements(), "Total elements should be mapped correctly");
        assertEquals(pagedResult.getTotalPages(), categoryResponsePagedResult.getTotalPages(), "Total pages should be mapped correctly");
        assertEquals(2, categoryResponsePagedResult.getContent().size(), "The size of the content list should match");
    }
}