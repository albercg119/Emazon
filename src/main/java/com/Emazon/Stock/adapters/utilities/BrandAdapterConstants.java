package com.Emazon.Stock.adapters.utilities;

public final class BrandAdapterConstants {
    private BrandAdapterConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Campo para ordenamiento
    public static final String NAME_FIELD = "nombre";

    // Valores por defecto para paginación
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 0;

    // Mensajes de error
    public static final String BRAND_NOT_FOUND_MESSAGE = "No se encontraron marcas";
    public static final String BRAND_ALREADY_EXISTS_MESSAGE = "La marca ya existe";
    public static final String INVALID_SORT_DIRECTION_MESSAGE = "La dirección de ordenamiento no es válida";
    public static final String INVALID_PAGE_SIZE_MESSAGE = "El tamaño de página debe ser mayor que 0";
    public static final String INVALID_PAGE_NUMBER_MESSAGE = "El número de página debe ser mayor o igual a 0";
    public static final String UTILITY_CLASS_ERROR = "Utility class";
}