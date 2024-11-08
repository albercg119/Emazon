package com.emzon.user.adapters.utilities;

public class InitialSetupControllerConstants {

    public static final String BASE_PATH = "/api/setup";
    public static final String ADMIN_PATH = "/admin";
    public static final String FIRST_ADMIN_PATH = "/first-admin";

    public static final String CREATE_ADMIN_SUMMARY = "Create admin user";
    public static final String CREATE_FIRST_ADMIN_SUMMARY = "Create first admin";

    public static final String CREATE_ADMIN_DESCRIPTION = "Creates a new admin user if email is not in use";
    public static final String CREATE_FIRST_ADMIN_DESCRIPTION = "Creates the first admin user if no users exist in the system";

    public static final String RESPONSE_200_CODE = "200";
    public static final String RESPONSE_400_CODE = "400";
    public static final String RESPONSE_500_CODE = "500";

    public static final String RESPONSE_200_ADMIN = "Admin user created successfully";
    public static final String RESPONSE_200_FIRST_ADMIN = "First admin created successfully";
    public static final String RESPONSE_400_EMAIL = "Email already in use";
    public static final String RESPONSE_400_FIRST_ADMIN = "First admin setup already completed";
    public static final String RESPONSE_500_MESSAGE = "Internal server error";

    public static final String EMAIL_IN_USE_BODY = "Email already in use";
    public static final String ADMIN_CREATED_BODY = "Admin user created successfully";
    public static final String SETUP_COMPLETED_BODY = "First admin setup has already been completed";
    public static final String FIRST_ADMIN_CREATED_BODY = "First admin user created successfully";
}