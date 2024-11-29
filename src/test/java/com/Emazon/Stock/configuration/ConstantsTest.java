package com.Emazon.Stock.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void shouldThrowIllegalStateException_WhenTryingToInstantiate() {
        Exception exception = assertThrows(IllegalStateException.class, Constants::new);
        assertEquals(Constants.UTILITY_CLASS_ERROR, exception.getMessage());
    }

    @Test
    void exceptionMessages_ShouldHaveCorrectValues() {
        assertEquals("No data was found in the database", Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The element indicated does not exist", Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The category you want to create already exists", Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertEquals("Field %s cannot be empty", Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        assertEquals("Field %s cannot receive negative values", Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        assertEquals("Utility class", Constants.UTILITY_CLASS_ERROR);
    }

    @Test
    void responseFields_ShouldHaveCorrectValues() {
        assertEquals("message", Constants.MESSAGE_FIELD);
        assertEquals("status", Constants.STATUS_FIELD);
        assertEquals("timestamp", Constants.TIMESTAMP_FIELD);
    }

    @Test
    void httpStatusMessages_ShouldHaveCorrectValues() {
        assertEquals("409 CONFLICT", Constants.CONFLICT_STATUS);
        assertEquals("400 BAD_REQUEST", Constants.BAD_REQUEST_STATUS);
        assertEquals("404 NOT_FOUND", Constants.NOT_FOUND_STATUS);
        assertEquals("500 INTERNAL_SERVER_ERROR", Constants.INTERNAL_SERVER_ERROR_STATUS);
    }

    @Test
    void responseKeys_ShouldHaveCorrectValues() {
        assertEquals("error", Constants.ERROR_RESPONSE_KEY);
        assertEquals("status", Constants.STATUS_RESPONSE_KEY);
        assertEquals("timestamp", Constants.TIMESTAMP_RESPONSE_KEY);
    }

    @Test
    void errorMessages_ShouldHaveCorrectValues() {
        assertEquals("An unexpected error occurred", Constants.GENERAL_ERROR_MESSAGE);
        assertEquals("Validation error occurred", Constants.VALIDATION_ERROR_MESSAGE);
        assertEquals("Invalid argument provided", Constants.ILLEGAL_ARGUMENT_MESSAGE);
    }

    @Test
    void constantsShouldNotBeNull() {
        // Exception Messages
        assertNotNull(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertNotNull(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertNotNull(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertNotNull(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        assertNotNull(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        assertNotNull(Constants.UTILITY_CLASS_ERROR);

        // Response Fields
        assertNotNull(Constants.MESSAGE_FIELD);
        assertNotNull(Constants.STATUS_FIELD);
        assertNotNull(Constants.TIMESTAMP_FIELD);

        // HTTP Status Messages
        assertNotNull(Constants.CONFLICT_STATUS);
        assertNotNull(Constants.BAD_REQUEST_STATUS);
        assertNotNull(Constants.NOT_FOUND_STATUS);
        assertNotNull(Constants.INTERNAL_SERVER_ERROR_STATUS);

        // Response Keys
        assertNotNull(Constants.ERROR_RESPONSE_KEY);
        assertNotNull(Constants.STATUS_RESPONSE_KEY);
        assertNotNull(Constants.TIMESTAMP_RESPONSE_KEY);

        // Error Messages
        assertNotNull(Constants.GENERAL_ERROR_MESSAGE);
        assertNotNull(Constants.VALIDATION_ERROR_MESSAGE);
        assertNotNull(Constants.ILLEGAL_ARGUMENT_MESSAGE);
    }

    @Test
    void formattedMessages_ShouldWorkCorrectly() {
        String fieldName = "username";
        assertEquals(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, fieldName),
                "Field username cannot be empty"
        );
        assertEquals(
                String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, fieldName),
                "Field username cannot receive negative values"
        );
    }

    @Test
    void statusMessages_ShouldFollowHttpConvention() {
        assertTrue(Constants.CONFLICT_STATUS.startsWith("409"));
        assertTrue(Constants.BAD_REQUEST_STATUS.startsWith("400"));
        assertTrue(Constants.NOT_FOUND_STATUS.startsWith("404"));
        assertTrue(Constants.INTERNAL_SERVER_ERROR_STATUS.startsWith("500"));
    }
}