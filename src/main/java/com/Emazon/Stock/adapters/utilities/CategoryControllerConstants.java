package com.Emazon.Stock.adapters.utilities;

public final class CategoryControllerConstants {
    private CategoryControllerConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_BASE_PATH = "/category";
    public static final String CATEGORY_PAGED_PATH = "/paged";

    public static final String CATEGORY_CREATED_SUMMARY = "Crear una nueva categoría";
    public static final String CATEGORIES_PAGED_SUMMARY = "Obtener categorías paginadas";
    public static final String CATEGORIES_UNPAGED_SUMMARY = "Obtener todas las categorías";

    public static final String CATEGORY_SUCCESS_CODE = "201";
    public static final String CATEGORIES_FOUND_CODE = "200";

    public static final String CATEGORY_CREATED_SUCCESSFULLY = "Categoría creada exitosamente";
    public static final String CATEGORIES_FOUND = "Categorías encontradas exitosamente";

    public static final String PARAM_PAGE = "page";
    public static final String PARAM_SIZE = "size";
    public static final String PARAM_SORT = "sort";

    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT = "asc";

    public static final String SORT_ASCENDING = "asc";
    public static final String SORT_DESCENDING = "desc";
}