package com.Emazon.Stock.configuration;

public final class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MESSAGE_FIELD = "message";
    public static final String STATUS_FIELD = "status";
    public static final String TIMESTAMP_FIELD = "timestamp";

    public static final String BAD_REQUEST = "400 BAD_REQUEST";
    public static final String NOT_FOUND = "404 NOT_FOUND";
    public static final String CONFLICT = "409 CONFLICT";
    public static final String INTERNAL_SERVER_ERROR = "500 INTERNAL_SERVER_ERROR";

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No se encontraron datos en la base de datos";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "El elemento indicado no existe";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "La categoría que intentas crear ya existe";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "El campo %s no puede estar vacío";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "El campo %s no puede tener valores negativos";
    public static final String VALIDATION_ERROR_FORMAT = "%s";
    public static final String UNEXPECTED_ERROR = "Ha ocurrido un error inesperado";

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
}