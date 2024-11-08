package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InitialSetupControllerConstantsTest {

    @Test
    void testPathConstants() {
        assertEquals("/api/setup", InitialSetupControllerConstants.BASE_PATH);
        assertEquals("/admin", InitialSetupControllerConstants.ADMIN_PATH);
        assertEquals("/first-admin", InitialSetupControllerConstants.FIRST_ADMIN_PATH);
    }

    @Test
    void testOperationSummaries() {
        assertEquals("Create admin user", InitialSetupControllerConstants.CREATE_ADMIN_SUMMARY);
        assertEquals("Create first admin", InitialSetupControllerConstants.CREATE_FIRST_ADMIN_SUMMARY);
    }

    @Test
    void testOperationDescriptions() {
        assertEquals("Creates a new admin user if email is not in use",
                InitialSetupControllerConstants.CREATE_ADMIN_DESCRIPTION);
        assertEquals("Creates the first admin user if no users exist in the system",
                InitialSetupControllerConstants.CREATE_FIRST_ADMIN_DESCRIPTION);
    }

    @Test
    void testResponseCodes() {
        assertEquals("200", InitialSetupControllerConstants.RESPONSE_200_CODE);
        assertEquals("400", InitialSetupControllerConstants.RESPONSE_400_CODE);
        assertEquals("500", InitialSetupControllerConstants.RESPONSE_500_CODE);
    }

    @Test
    void testResponseMessages() {
        assertEquals("Admin user created successfully", InitialSetupControllerConstants.RESPONSE_200_ADMIN);
        assertEquals("First admin created successfully", InitialSetupControllerConstants.RESPONSE_200_FIRST_ADMIN);
        assertEquals("Email already in use", InitialSetupControllerConstants.RESPONSE_400_EMAIL);
        assertEquals("First admin setup already completed", InitialSetupControllerConstants.RESPONSE_400_FIRST_ADMIN);
        assertEquals("Internal server error", InitialSetupControllerConstants.RESPONSE_500_MESSAGE);
    }

    @Test
    void testResponseBodies() {
        assertEquals("Email already in use", InitialSetupControllerConstants.EMAIL_IN_USE_BODY);
        assertEquals("Admin user created successfully", InitialSetupControllerConstants.ADMIN_CREATED_BODY);
        assertEquals("First admin setup has already been completed", InitialSetupControllerConstants.SETUP_COMPLETED_BODY);
        assertEquals("First admin user created successfully", InitialSetupControllerConstants.FIRST_ADMIN_CREATED_BODY);
    }
}