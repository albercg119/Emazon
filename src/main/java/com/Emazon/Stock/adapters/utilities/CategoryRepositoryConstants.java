package com.Emazon.Stock.adapters.utilities;

public final class CategoryRepositoryConstants {
    private CategoryRepositoryConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PARAM_NOMBRE = "nombre";
    public static final String PARAM_ID = "id";

    public static final String FIELD_ID = "id";
    public static final String FIELD_NOMBRE = "nombre";

    public static final String CATEGORY_TABLE = "categories";

    public static final String FIND_BY_NOMBRE_QUERY =
            "SELECT c FROM CategoryEntity c WHERE LOWER(c." + FIELD_NOMBRE + ") = LOWER(:" + PARAM_NOMBRE + ")";

    public static final String EXIST_BY_NAME_EXCLUDING_ID_QUERY =
            "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
                    "FROM CategoryEntity c " +
                    "WHERE LOWER(c." + FIELD_NOMBRE + ") = LOWER(:" + PARAM_NOMBRE + ") " +
                    "AND c." + FIELD_ID + " != :" + PARAM_ID;
}