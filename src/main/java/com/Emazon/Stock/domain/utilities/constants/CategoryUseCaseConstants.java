package com.Emazon.Stock.domain.utilities.constants;

public final class CategoryUseCaseConstants {
    private CategoryUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME = "nombre";
    public static final String FIELD_DESCRIPTION = "descripcion";
    public static final String FIELD_ID = "id";

    public static final int MAX_CATEGORY_NAME_LENGTH = 50;
    public static final int MAX_CATEGORY_DESCRIPTION_LENGTH = 90;
    public static final int MIN_CATEGORY_NAME_LENGTH = 1;

    public static final String DEFAULT_CATEGORY_DESCRIPTION = "Sin descripción";

    public static final String CATEGORY_NAME_PATTERN = "^[a-zA-Z0-9\\s]{1,50}$";

    public static final String CATEGORY_NULL_EXCEPTION_MESSAGE = "La categoría no puede ser nula";
    public static final String CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE = "El nombre de la categoría no puede estar vacío";
    public static final String CATEGORY_NAME_LENGTH_MESSAGE =
            String.format("La longitud del nombre debe estar entre %d y %d caracteres",
                    MIN_CATEGORY_NAME_LENGTH, MAX_CATEGORY_NAME_LENGTH);
    public static final String CATEGORY_NAME_INVALID_FORMAT_MESSAGE =
            "El nombre solo puede contener letras, números y espacios";
    public static final String CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE =
            "La descripción de la categoría no puede estar vacía";
    public static final String CATEGORY_DESCRIPTION_LENGTH_MESSAGE =
            String.format("La descripción no puede exceder los %d caracteres",
                    MAX_CATEGORY_DESCRIPTION_LENGTH);
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "El nombre de la categoría debe ser único";

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final String INVALID_PAGE_SIZE_MESSAGE = "El tamaño de página debe ser mayor que 0";
    public static final String INVALID_PAGE_NUMBER_MESSAGE = "El número de página debe ser mayor o igual a 0";

    public static final String INVALID_SORT_DIRECTION_MESSAGE = "La dirección de ordenamiento no es válida";
}