package com.Emazon.Stock.domain.utilities.constants;

public final class ArticleUseCaseConstants {
    private ArticleUseCaseConstants() {
        throw new IllegalStateException(UTILITY_CLASS_ERROR);
    }

    public static final String EMPTY_FIELDS_MESSAGE = "El artículo no puede tener campos vacíos o nulos";
    public static final String DUPLICATE_CATEGORIES_MESSAGE = "Hay categorías duplicadas en la lista";
    public static final String CATEGORY_COUNT_MESSAGE = "El artículo debe tener entre 1 y 3 categorías";
    public static final String ARTICLE_NAME_UNIQUE_MESSAGE = "El nombre del artículo debe ser único";
    public static final String INVALID_PRICE_MESSAGE = "El precio del artículo debe ser mayor que cero";
    public static final String INVALID_STOCK_MESSAGE = "El stock del artículo debe ser mayor que cero";

    public static final int MIN_CATEGORIES = 1;
    public static final int MAX_CATEGORIES = 3;
    public static final double MIN_PRICE = 0.0;
    public static final int MIN_STOCK = 0;

    public static final String UTILITY_CLASS_ERROR = "Clase de utilidad";
}