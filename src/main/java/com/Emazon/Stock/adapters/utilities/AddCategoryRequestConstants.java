package com.Emazon.Stock.adapters.utilities;

public final class AddCategoryRequestConstants {
    private AddCategoryRequestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";

    public static final String CATEGORY_NAME_REQUIRED = "El nombre de la categoría es requerido";
    public static final String CATEGORY_NAME_MAX_SIZE = "El nombre de la categoría no debe exceder los 50 caracteres";
    public static final String CATEGORY_DESCRIPTION_REQUIRED = "La descripción de la categoría es requerida";
    public static final String CATEGORY_DESCRIPTION_MAX_SIZE = "La descripción no debe exceder los 90 caracteres";

    public static final int CATEGORY_NAME_MAX_LENGTH = 50;
    public static final int CATEGORY_DESCRIPTION_MAX_LENGTH = 90;
}