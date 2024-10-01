package com.Emazon.Stock.adapters.utilities;

public class ArticleControllerConstants {
    public static final String PATH_ARTICLE = "/article";
    public static final String ARTICLE_CREATED_MESSAGE = "Article successfully created";
    public static final String INVALID_INPUT_DATA_MESSAGE = "Invalid input data";
    public static final String ARTICLE_NAME_UNIQUE_MESSAGE = "Article name must be unique";
    public static final String ARTICLE_NOT_FOUND_MESSAGE = "Article not found"; // Mensaje para artículo no encontrado
    public static final String ARTICLE_SAVE_ERROR_MESSAGE = "Error saving article"; // Mensaje de error al guardar

    public static final String OPERATION_SUMMARY_ADD_ARTICLE = "Add a new article";
    public static final String OPERATION_DESCRIPTION_ADD_ARTICLE = "Add a new article to the inventory.";
    public static final String OPERATION_SUMMARY_GET_PAGED_ARTICLES = "Get paginated articles"; // Resumen para obtener artículos paginados
    public static final String OPERATION_DESCRIPTION_GET_PAGED_ARTICLES = "Retrieve a paginated list of articles."; // Descripción para obtener artículos paginados

    public static final String RESPONSE_CODE_200_DESCRIPTION = "Articles found"; // Descripción para código 200
    public static final String RESPONSE_CODE_201_DESCRIPTION = ARTICLE_CREATED_MESSAGE;
    public static final String RESPONSE_CODE_400_DESCRIPTION = INVALID_INPUT_DATA_MESSAGE;
    public static final String RESPONSE_CODE_404_DESCRIPTION = ARTICLE_NOT_FOUND_MESSAGE; // Descripción para código 404
    public static final String RESPONSE_CODE_409_DESCRIPTION = ARTICLE_NAME_UNIQUE_MESSAGE;

    public static final String RESPONSE_CODE_200 = "200"; // Código de respuesta 200
    public static final String RESPONSE_CODE_201 = "201";
    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_CODE_404 = "404"; // Código para no encontrado
    public static final String RESPONSE_CODE_409 = "409";
}