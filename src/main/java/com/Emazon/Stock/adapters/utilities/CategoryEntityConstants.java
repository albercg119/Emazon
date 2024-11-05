package com.Emazon.Stock.adapters.utilities;

public final class CategoryEntityConstants {
    private CategoryEntityConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TABLE_NAME = "category";
    public static final String NOMBRE_COLUMN = "nombre";
    public static final String ID_COLUMN = "id";
    public static final String DESCRIPCION_COLUMN = "descripcion";

    public static final String UTILITY_CLASS_ERROR = "Utility class";

    public static final int NOMBRE_MAX_LENGTH = 50;
    public static final int DESCRIPCION_MAX_LENGTH = 120;
}