package com.Emazon.Stock.adapters.utilities;

public class CategoryRepositoryConstants {
    private CategoryRepositoryConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Query Parameters
    public static final String PARAM_NOMBRE = "nombre";
    public static final String PARAM_ID = "id";

    // Entity Fields
    public static final String FIELD_ID = "id";
    public static final String FIELD_NOMBRE = "nombre";

    // JPQL Queries
    public static final String EXIST_BY_NAME_EXCLUDING_ID_QUERY =
            "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
                    "FROM CategoryEntity c " +
                    "WHERE LOWER(c." + FIELD_NOMBRE + ") = LOWER(:" + PARAM_NOMBRE + ") " +
                    "AND c." + FIELD_ID + " != :" + PARAM_ID + "";

    // Table Names
    public static final String CATEGORY_TABLE = "categories";
}
