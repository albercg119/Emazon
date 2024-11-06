package com.Emazon.Stock.domain.utilities.constants;

public class BrandUseCaseConstants {
    private BrandUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BRAND_NULL_ERROR = "La marca no puede ser nula";
    public static final String BRAND_NAME_NULL_OR_EMPTY_ERROR = "El nombre de la marca no puede ser nulo o vacío";
    public static final String BRAND_NAME_LENGTH_ERROR = "El nombre de la marca no puede exceder los 50 caracteres";
    public static final String BRAND_DESCRIPTION_NULL_OR_EMPTY_ERROR = "La descripción de la marca no puede ser nula o vacía";
    public static final String BRAND_DESCRIPTION_LENGTH_ERROR = "La descripción de la marca no puede exceder los 120 caracteres";
    public static final String BRAND_ALREADY_EXISTS_ERROR = "El nombre de la marca ya existe en el sistema";

    public static final int BRAND_NAME_MAX_LENGTH = 50;
    public static final int BRAND_DESCRIPTION_MAX_LENGTH = 120;
}