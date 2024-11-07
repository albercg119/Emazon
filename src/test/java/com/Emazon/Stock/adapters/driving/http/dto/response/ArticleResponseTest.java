package com.Emazon.Stock.adapters.driving.http.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleResponseTest {

    private ArticleResponse articleResponse;

    @BeforeEach
    void setUp() {
        articleResponse = new ArticleResponse();
    }

    @Test
    void shouldSetAndGetId() {
        Long expectedId = 1L;
        articleResponse.setId(expectedId);
        assertEquals(expectedId, articleResponse.getId());
    }

    @Test
    void shouldSetAndGetName() {
        String expectedName = "Test Article";
        articleResponse.setName(expectedName);
        assertEquals(expectedName, articleResponse.getName());
    }

    @Test
    void shouldSetAndGetDescription() {
        String expectedDescription = "Test Description";
        articleResponse.setDescription(expectedDescription);
        assertEquals(expectedDescription, articleResponse.getDescription());
    }

    @Test
    void shouldSetAndGetPrice() {
        Double expectedPrice = 99.99;
        articleResponse.setPrice(expectedPrice);
        assertEquals(expectedPrice, articleResponse.getPrice());
    }

    @Test
    void shouldSetAndGetBrandName() {
        String expectedBrandName = "Test Brand";
        articleResponse.setBrandName(expectedBrandName);
        assertEquals(expectedBrandName, articleResponse.getBrandName());
    }

    @Test
    void shouldSetAndGetCategories() {
        List<CategorySimpleResponse> expectedCategories = Arrays.asList(
                new CategorySimpleResponse(1L, "Category 1"),
                new CategorySimpleResponse(2L, "Category 2")
        );
        articleResponse.setCategories(expectedCategories);

        assertNotNull(articleResponse.getCategories());
        assertEquals(expectedCategories.size(), articleResponse.getCategories().size());
        assertEquals(expectedCategories, articleResponse.getCategories());
    }

    @Test
    void shouldInitializeWithNullValues() {
        ArticleResponse newResponse = new ArticleResponse();
        assertNull(newResponse.getId());
        assertNull(newResponse.getName());
        assertNull(newResponse.getDescription());
        assertNull(newResponse.getPrice());
        assertNull(newResponse.getBrandName());
        assertNull(newResponse.getCategories());
    }
}