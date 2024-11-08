package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerConstantsTest {

    @Test
    void testPathConstants() {
        assertEquals("/auth", AuthControllerConstants.BASE_PATH);
        assertEquals("/login", AuthControllerConstants.LOGIN_PATH);
    }

    @Test
    void testOperationDetails() {
        assertEquals("User login", AuthControllerConstants.LOGIN_SUMMARY);
        assertEquals("Authenticates a user and returns a JWT token",
                AuthControllerConstants.LOGIN_DESCRIPTION);
    }

    @Test
    void testResponseCodes() {
        assertEquals("200", AuthControllerConstants.RESPONSE_200_CODE);
        assertEquals("400", AuthControllerConstants.RESPONSE_400_CODE);
        assertEquals("500", AuthControllerConstants.RESPONSE_500_CODE);
    }

    @Test
    void testResponseMessages() {
        assertEquals("Successfully authenticated", AuthControllerConstants.RESPONSE_200_MESSAGE);
        assertEquals("Invalid credentials", AuthControllerConstants.RESPONSE_400_MESSAGE);
        assertEquals("Internal server error", AuthControllerConstants.RESPONSE_500_MESSAGE);
    }

    @Test
    void testErrorMessages() {
        assertEquals("USER_DISABLED", AuthControllerConstants.USER_DISABLED_MESSAGE);
        assertEquals("INVALID_CREDENTIALS", AuthControllerConstants.INVALID_CREDENTIALS_MESSAGE);
    }
}