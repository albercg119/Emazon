package com.Emazon.Stock.configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void constantValues_ShouldBeCorrect() {
        // Assert exception messages
        assertEquals("No data was found in the database",
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The element indicated does not exist",
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The category you want to create already exists",
                Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertEquals("The brand you want to create already exists",
                Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);

        // Assert validation messages
        assertEquals("Validation errors occurred",
                Constants.VALIDATION_ERROR_MESSAGE);
        assertEquals("Field %s cannot be empty",
                Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        assertEquals("Field %s cannot receive negative values",
                Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);

        // Assert response keys
        assertEquals("message", Constants.RESPONSE_MESSAGE_KEY);
        assertEquals("status", Constants.RESPONSE_STATUS_KEY);
        assertEquals("timestamp", Constants.RESPONSE_TIMESTAMP_KEY);

        // Assert status codes
        assertEquals("409 CONFLICT", Constants.STATUS_CONFLICT);
        assertEquals("400 BAD REQUEST", Constants.STATUS_BAD_REQUEST);
        assertEquals("404 NOT FOUND", Constants.STATUS_NOT_FOUND);
        assertEquals("500 INTERNAL SERVER ERROR", Constants.STATUS_INTERNAL_SERVER_ERROR);
    }
}