package com.emzon.user.configuration.utilities.constants;

public class ExceptionConstants {

    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    public static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred";
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "Invalid argument provided";
    public static final String NULL_PARAMETER_MESSAGE = "Required parameter cannot be null";
    public static final String INVALID_FORMAT_MESSAGE = "Invalid format provided";


    public static final String URI_PREFIX = "uri=";
    public static final String ERROR_PREFIX = "error=";

    private ExceptionConstants() {

    }
}