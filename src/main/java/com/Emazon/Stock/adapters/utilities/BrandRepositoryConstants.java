package com.Emazon.Stock.adapters.utilities;

public class BrandRepositoryConstants {

    public static final String EXISTS_BY_NOMBRE_EXCLUDING_ID =
            "SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BrandEntity b WHERE LOWER(b.nombre) = LOWER(:nombre) AND b.id != :id";

    public static final String PARAM_NOMBRE = "nombre";
    public static final String PARAM_ID = "id";

    public static final String ENTITY_ALIAS = "b";

    public static final String NOMBRE_FIELD = "nombre";
    public static final String ID_FIELD = "id";
}