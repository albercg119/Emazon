package com.Emazon.Stock.adapters.utilities;

public class AddArticleRequestConstants {
    private AddArticleRequestConstants() {
        throw new IllegalStateException(UTILITY_CLASS_ERROR);
    }

    public static final String NAME_NOT_NULL_MESSAGE = "El nombre del artículo no puede ser nulo";
    public static final String NAME_NOT_BLANK_MESSAGE = "El nombre del artículo es requerido";
    public static final String NAME_SIZE_MESSAGE = "El nombre del artículo debe tener entre %d y %d caracteres";
    public static final String DESCRIPTION_NOT_NULL_MESSAGE = "La descripción del artículo no puede ser nula";
    public static final String DESCRIPTION_NOT_BLANK_MESSAGE = "La descripción del artículo es requerida";
    public static final String DESCRIPTION_SIZE_MESSAGE = "La descripción del artículo debe tener entre %d y %d caracteres";
    public static final String STOCK_NOT_NULL_MESSAGE = "El stock del artículo no puede ser nulo";
    public static final String PRICE_NOT_NULL_MESSAGE = "El precio del artículo no puede ser nulo";
    public static final String PRICE_DECIMAL_MIN_MESSAGE = "El precio del artículo debe ser mayor que %s";
    public static final String CATEGORIES_NOT_NULL_MESSAGE = "Las categorías del artículo no pueden ser nulas";
    public static final String BRAND_NOT_NULL_MESSAGE = "La marca del artículo no puede ser nula";

    public static final int NAME_MIN_LENGTH = 1;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MIN_LENGTH = 1;
    public static final int DESCRIPTION_MAX_LENGTH = 90;

    public static final String MIN_PRICE = "0.0";

    public static final String UTILITY_CLASS_ERROR = "Clase de utilidad";
}