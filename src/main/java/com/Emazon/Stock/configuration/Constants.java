package com.Emazon.Stock.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String RESPONSE_STATUS_KEY = "status";
    public static final String RESPONSE_TIMESTAMP_KEY = "timestamp";

    public static final String CONFLICT_STATUS = "409 CONFLICT";
    public static final String NOT_FOUND_STATUS = "404 NOT FOUND";
    public static final String BAD_REQUEST_STATUS = "400 BAD_REQUEST";
    public static final String INTERNAL_SERVER_ERROR_STATUS = "500 INTERNAL_SERVER_ERROR";

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The element indicated does not exist";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s cannot be empty";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field %s cannot receive negative values";

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";

    public static final String VALIDATION_ERROR_MESSAGE = "Validation error occurred";
    public static final String GENERAL_ERROR_MESSAGE = "An unexpected error occurred";
}