package com.Emazon.Stock.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The element indicated does not exist";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category you want to create already exists";
    public static final String BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The brand you want to create already exists";
    public static final String ARTICLE_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The article you want to create already exists";

    public static final String VALIDATION_ERROR_MESSAGE = "Validation errors occurred";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s cannot be empty";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field %s cannot receive negative values";

    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String RESPONSE_STATUS_KEY = "status";
    public static final String RESPONSE_TIMESTAMP_KEY = "timestamp";

    public static final String STATUS_CONFLICT = "409 CONFLICT";
    public static final String STATUS_BAD_REQUEST = "400 BAD REQUEST";
    public static final String STATUS_NOT_FOUND = "404 NOT FOUND";
    public static final String STATUS_INTERNAL_SERVER_ERROR = "500 INTERNAL SERVER ERROR";

    public static final String UTILITY_CLASS_ERROR = "Utility class";
}