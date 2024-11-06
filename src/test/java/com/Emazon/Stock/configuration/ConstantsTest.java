package com.Emazon.Stock.configuration;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void verifyExceptionMessages() {
        assertEquals("No data was found in the database", Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The element indicated does not exist", Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE);
        assertEquals("The category you want to create already exists", Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertEquals("The brand you want to create already exists", Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        assertEquals("The article you want to create already exists", Constants.ARTICLE_ALREADY_EXISTS_EXCEPTION_MESSAGE);
    }

    @Test
    void verifyValidationMessages() {
        assertEquals("Validation errors occurred", Constants.VALIDATION_ERROR_MESSAGE);
        String fieldName = "testField";
        assertEquals(String.format("Field %s cannot be empty", fieldName),
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, fieldName));
        assertEquals(String.format("Field %s cannot receive negative values", fieldName),
                String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, fieldName));
    }

    @Test
    void verifyResponseKeys() {
        assertEquals("message", Constants.RESPONSE_MESSAGE_KEY);
        assertEquals("status", Constants.RESPONSE_STATUS_KEY);
        assertEquals("timestamp", Constants.RESPONSE_TIMESTAMP_KEY);
    }

    @Test
    void verifyHttpStatusCodes() {
        assertEquals("409 CONFLICT", Constants.STATUS_CONFLICT);
        assertEquals("400 BAD REQUEST", Constants.STATUS_BAD_REQUEST);
        assertEquals("404 NOT FOUND", Constants.STATUS_NOT_FOUND);
        assertEquals("500 INTERNAL SERVER ERROR", Constants.STATUS_INTERNAL_SERVER_ERROR);
    }

    @Test
    void constructorShouldBePrivate() {
        Constructor<Constants> constructor = null;
        try {
            constructor = Constants.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }

    @Test
    void constructorShouldThrowException() {
        Constructor<Constants> constructor = null;
        try {
            constructor = Constants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

            Throwable cause = exception.getCause();
            assertNotNull(cause);
            assertEquals(IllegalStateException.class, cause.getClass());
            assertEquals(Constants.UTILITY_CLASS_ERROR, cause.getMessage());
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }
}