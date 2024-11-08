package com.emzon.user.adapters.utilities;

public class AuthControllerConstants {

    public static final String BASE_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";

    public static final String LOGIN_SUMMARY = "User login";
    public static final String LOGIN_DESCRIPTION = "Authenticates a user and returns a JWT token";

    public static final String RESPONSE_200_CODE = "200";
    public static final String RESPONSE_400_CODE = "400";
    public static final String RESPONSE_500_CODE = "500";

    public static final String RESPONSE_200_MESSAGE = "Successfully authenticated";
    public static final String RESPONSE_400_MESSAGE = "Invalid credentials";
    public static final String RESPONSE_500_MESSAGE = "Internal server error";

    public static final String USER_DISABLED_MESSAGE = "USER_DISABLED";
    public static final String INVALID_CREDENTIALS_MESSAGE = "INVALID_CREDENTIALS";
}