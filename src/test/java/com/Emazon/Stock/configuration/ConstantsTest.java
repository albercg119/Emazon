package com.Emazon.Stock.configuration;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void exceptionMessages_ShouldHaveCorrectValues() {
        assertEquals("No data was found in the database", Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The element indicated does not exist", Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The category you want to create already exists", Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertEquals("The brand you want to create already exists", Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);
    }

    @Test
    void validationMessages_ShouldHaveCorrectValues() {
        assertEquals("Validation errors occurred", Constants.VALIDATION_ERROR_MESSAGE);
        assertEquals("Field %s cannot be empty", Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        assertEquals("Field %s cannot receive negative values", Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
    }

    @Test
    void responseKeys_ShouldHaveCorrectValues() {
        assertEquals("message", Constants.RESPONSE_MESSAGE_KEY);
        assertEquals("status", Constants.RESPONSE_STATUS_KEY);
        assertEquals("timestamp", Constants.RESPONSE_TIMESTAMP_KEY);
    }

    @Test
    void httpStatusCodes_ShouldHaveCorrectValues() {
        assertEquals("409 CONFLICT", Constants.STATUS_CONFLICT);
        assertEquals("400 BAD REQUEST", Constants.STATUS_BAD_REQUEST);
        assertEquals("404 NOT FOUND", Constants.STATUS_NOT_FOUND);
        assertEquals("500 INTERNAL SERVER ERROR", Constants.STATUS_INTERNAL_SERVER_ERROR);
    }

    @Test
    void classShouldBeFinal() {
        assertTrue(Modifier.isFinal(Constants.class.getModifiers()));
    }

    @Test
    void constantsShouldNotBeNull() {
        // Exception Messages
        assertNotNull(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertNotNull(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertNotNull(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertNotNull(Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertNotNull(Constants.VALIDATION_ERROR_MESSAGE);
        assertNotNull(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE);
        assertNotNull(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);

        // Response Keys
        assertNotNull(Constants.RESPONSE_MESSAGE_KEY);
        assertNotNull(Constants.RESPONSE_STATUS_KEY);
        assertNotNull(Constants.RESPONSE_TIMESTAMP_KEY);

        // HTTP Status
        assertNotNull(Constants.STATUS_CONFLICT);
        assertNotNull(Constants.STATUS_BAD_REQUEST);
        assertNotNull(Constants.STATUS_NOT_FOUND);
        assertNotNull(Constants.STATUS_INTERNAL_SERVER_ERROR);
    }

    @Test
    void formattedMessages_ShouldWorkCorrectly() {
        String fieldName = "testField";
        assertEquals(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, fieldName),
                "Field testField cannot be empty"
        );
        assertEquals(
                String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, fieldName),
                "Field testField cannot receive negative values"
        );
    }
}