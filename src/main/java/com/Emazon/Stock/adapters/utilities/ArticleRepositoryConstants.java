package com.Emazon.Stock.adapters.utilities;

public final class ArticleRepositoryConstants {
    private ArticleRepositoryConstants() {
        throw new IllegalStateException(UTILITY_CLASS_ERROR);
    }

    public static final String FIND_BY_NOMBRE_METHOD = "findByNombre";

    public static final String NOMBRE_PARAM = "nombre";

    public static final String CATEGORIES_ATTRIBUTE = "categories";
    public static final String BRAND_ATTRIBUTE = "brand";

    public static final String UTILITY_CLASS_ERROR = "Utility class";
}