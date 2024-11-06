package com.Emazon.Stock.adapters.utilities;

public final class CategoryAdapterConstants {
    private CategoryAdapterConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NAME_FIELD = "nombre";

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 0;

    public static final String CATEGORY_NOT_FOUND_MESSAGE = "No se encontraron categorías";
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "La categoría ya existe";
    public static final String INVALID_SORT_DIRECTION_MESSAGE = "La dirección de ordenamiento no es válida";
    public static final String INVALID_PAGE_SIZE_MESSAGE = "El tamaño de página debe ser mayor que 0";
    public static final String INVALID_PAGE_NUMBER_MESSAGE = "El número de página debe ser mayor o igual a 0";
    public static final String UTILITY_CLASS_ERROR = "Utility class";
}