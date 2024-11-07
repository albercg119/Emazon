package com.Emazon.Stock.domain.utilities.constants;

public final class CategoryUseCaseConstants {
    private CategoryUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_NULL_EXCEPTION_MESSAGE = "La categoría no puede ser nula";
    public static final String CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE = "El nombre de la categoría no puede ser nulo o vacío";
    public static final String CATEGORY_NAME_LENGTH_MESSAGE = "El nombre de la categoría no puede tener más de 50 caracteres";
    public static final String CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE = "La descripción de la categoría no puede ser nula o vacía";
    public static final String CATEGORY_DESCRIPTION_LENGTH_MESSAGE = "La descripción de la categoría no puede tener más de 90 caracteres";
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "Ya existe una categoría con ese nombre";

    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_DESCRIPTION_LENGTH = 90;
}