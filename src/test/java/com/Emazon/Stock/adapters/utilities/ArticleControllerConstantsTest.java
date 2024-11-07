package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArticleControllerConstantsTest {

    @Test
    void shouldHaveCorrectMessageValues() {
        assertEquals("Article successfully created", ArticleControllerConstants.ARTICLE_CREATED_MESSAGE);
        assertEquals("Invalid input data", ArticleControllerConstants.INVALID_INPUT_DATA_MESSAGE);
        assertEquals("Article name must be unique", ArticleControllerConstants.ARTICLE_NAME_UNIQUE_MESSAGE);
        assertEquals("Article not found", ArticleControllerConstants.ARTICLE_NOT_FOUND_MESSAGE);
        assertEquals("Error saving article", ArticleControllerConstants.ARTICLE_SAVE_ERROR_MESSAGE);
    }

    @Test
    void shouldHaveCorrectOperationValues() {
        assertEquals("Add a new article", ArticleControllerConstants.OPERATION_SUMMARY_ADD_ARTICLE);
        assertEquals("Add a new article to the inventory", ArticleControllerConstants.OPERATION_DESCRIPTION_ADD_ARTICLE);
        assertEquals("Get paginated articles", ArticleControllerConstants.OPERATION_SUMMARY_GET_PAGED_ARTICLES);
        assertEquals("Retrieve a paginated list of articles with optional sorting",
                ArticleControllerConstants.OPERATION_DESCRIPTION_GET_PAGED_ARTICLES);
    }

    @Test
    void shouldHaveCorrectParameterNames() {
        assertEquals("page", ArticleControllerConstants.PAGE_PARAM);
        assertEquals("size", ArticleControllerConstants.SIZE_PARAM);
        assertEquals("sortBy", ArticleControllerConstants.SORT_BY_PARAM);
        assertEquals("ascending", ArticleControllerConstants.ASCENDING_PARAM);
    }

    @Test
    void shouldHaveCorrectDefaultValues() {
        assertEquals("0", ArticleControllerConstants.DEFAULT_PAGE);
        assertEquals("10", ArticleControllerConstants.DEFAULT_SIZE);
        assertEquals("name", ArticleControllerConstants.DEFAULT_SORT_BY);
        assertEquals("true", ArticleControllerConstants.DEFAULT_SORT_DIRECTION);
    }

    @Test
    void shouldHaveCorrectParameterDescriptions() {
        assertEquals("Page number (zero-based)", ArticleControllerConstants.PARAM_PAGE_DESCRIPTION);
        assertEquals("Number of items per page", ArticleControllerConstants.PARAM_SIZE_DESCRIPTION);
        assertEquals("Field to sort by (name, brandName, categoryName)",
                ArticleControllerConstants.PARAM_SORT_BY_DESCRIPTION);
        assertEquals("Sort direction (true for ascending, false for descending)",
                ArticleControllerConstants.PARAM_ASCENDING_DESCRIPTION);
    }

    @Test
    void shouldHaveCorrectResponseCodes() {
        assertEquals("200", ArticleControllerConstants.RESPONSE_CODE_200);
        assertEquals("201", ArticleControllerConstants.RESPONSE_CODE_201);
        assertEquals("400", ArticleControllerConstants.RESPONSE_CODE_400);
        assertEquals("404", ArticleControllerConstants.RESPONSE_CODE_404);
        assertEquals("409", ArticleControllerConstants.RESPONSE_CODE_409);
    }

    @Test
    void shouldHaveCorrectResponseDescriptions() {
        assertEquals("Articles successfully retrieved", ArticleControllerConstants.RESPONSE_CODE_200_DESCRIPTION);
        assertEquals(ArticleControllerConstants.ARTICLE_CREATED_MESSAGE,
                ArticleControllerConstants.RESPONSE_CODE_201_DESCRIPTION);
        assertEquals(ArticleControllerConstants.INVALID_INPUT_DATA_MESSAGE,
                ArticleControllerConstants.RESPONSE_CODE_400_DESCRIPTION);
        assertEquals(ArticleControllerConstants.ARTICLE_NOT_FOUND_MESSAGE,
                ArticleControllerConstants.RESPONSE_CODE_404_DESCRIPTION);
        assertEquals(ArticleControllerConstants.ARTICLE_NAME_UNIQUE_MESSAGE,
                ArticleControllerConstants.RESPONSE_CODE_409_DESCRIPTION);
    }
}