package com.Emazon.Stock.configuration;

public final class Constants {
    Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The element indicated does not exist";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s cannot be empty";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field %s cannot receive negative values";
    public static final String UTILITY_CLASS_ERROR = "Utility class";

    public static final String MESSAGE_FIELD = "message";
    public static final String STATUS_FIELD = "status";
    public static final String TIMESTAMP_FIELD = "timestamp";

    public static final String CONFLICT_STATUS = "409 CONFLICT";
    public static final String BAD_REQUEST_STATUS = "400 BAD_REQUEST";
    public static final String NOT_FOUND_STATUS = "404 NOT_FOUND";
    public static final String INTERNAL_SERVER_ERROR_STATUS = "500 INTERNAL_SERVER_ERROR";

    public static final String ERROR_RESPONSE_KEY = "error";
    public static final String STATUS_RESPONSE_KEY = "status";
    public static final String TIMESTAMP_RESPONSE_KEY = "timestamp";

    public static final String GENERAL_ERROR_MESSAGE = "An unexpected error occurred";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation error occurred";
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "Invalid argument provided";
}