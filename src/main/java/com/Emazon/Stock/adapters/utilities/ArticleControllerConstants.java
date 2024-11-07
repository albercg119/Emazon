package com.Emazon.Stock.adapters.utilities;

public class ArticleControllerConstants {
    private ArticleControllerConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ARTICLE_CREATED_MESSAGE = "Article successfully created";
    public static final String INVALID_INPUT_DATA_MESSAGE = "Invalid input data";
    public static final String ARTICLE_NAME_UNIQUE_MESSAGE = "Article name must be unique";
    public static final String ARTICLE_NOT_FOUND_MESSAGE = "Article not found";
    public static final String ARTICLE_SAVE_ERROR_MESSAGE = "Error saving article";

    public static final String OPERATION_SUMMARY_ADD_ARTICLE = "Add a new article";
    public static final String OPERATION_DESCRIPTION_ADD_ARTICLE = "Add a new article to the inventory";
    public static final String OPERATION_SUMMARY_GET_PAGED_ARTICLES = "Get paginated articles";
    public static final String OPERATION_DESCRIPTION_GET_PAGED_ARTICLES = "Retrieve a paginated list of articles with optional sorting";

    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "name";
    public static final String DEFAULT_SORT_DIRECTION = "true";

    public static final String PARAM_PAGE_DESCRIPTION = "Page number (zero-based)";
    public static final String PARAM_SIZE_DESCRIPTION = "Number of items per page";
    public static final String PARAM_SORT_BY_DESCRIPTION = "Field to sort by (name, brandName, categoryName)";
    public static final String PARAM_ASCENDING_DESCRIPTION = "Sort direction (true for ascending, false for descending)";

    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_CODE_409 = "409";

    public static final String RESPONSE_CODE_200_DESCRIPTION = "Articles successfully retrieved";
    public static final String RESPONSE_CODE_201_DESCRIPTION = ARTICLE_CREATED_MESSAGE;
    public static final String RESPONSE_CODE_400_DESCRIPTION = INVALID_INPUT_DATA_MESSAGE;
    public static final String RESPONSE_CODE_404_DESCRIPTION = ARTICLE_NOT_FOUND_MESSAGE;
    public static final String RESPONSE_CODE_409_DESCRIPTION = ARTICLE_NAME_UNIQUE_MESSAGE;

    public static final String PAGE_PARAM = "page";
    public static final String SIZE_PARAM = "size";
    public static final String SORT_BY_PARAM = "sortBy";
    public static final String ASCENDING_PARAM = "ascending";
}