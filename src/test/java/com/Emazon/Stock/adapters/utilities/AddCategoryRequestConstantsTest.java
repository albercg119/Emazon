package com.Emazon.Stock.adapters.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class AddCategoryRequestConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<AddCategoryRequestConstants> constructor = AddCategoryRequestConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });

        Throwable cause = thrown.getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("Utility class", cause.getMessage());
    }

    @Test
    void fieldConstants_ShouldHaveCorrectValues() {
        assertEquals("name", AddCategoryRequestConstants.NAME_FIELD);
        assertEquals("description", AddCategoryRequestConstants.DESCRIPTION_FIELD);
    }

    @Test
    void validationMessageConstants_ShouldHaveCorrectValues() {
        assertEquals("El nombre de la categoría es requerido", AddCategoryRequestConstants.CATEGORY_NAME_REQUIRED);
        assertEquals("El nombre de la categoría no debe exceder los 50 caracteres", AddCategoryRequestConstants.CATEGORY_NAME_MAX_SIZE);
        assertEquals("La descripción de la categoría es requerida", AddCategoryRequestConstants.CATEGORY_DESCRIPTION_REQUIRED);
        assertEquals("La descripción no debe exceder los 90 caracteres", AddCategoryRequestConstants.CATEGORY_DESCRIPTION_MAX_SIZE);
    }

    @Test
    void lengthConstants_ShouldHaveCorrectValues() {
        assertEquals(50, AddCategoryRequestConstants.CATEGORY_NAME_MAX_LENGTH);
        assertEquals(90, AddCategoryRequestConstants.CATEGORY_DESCRIPTION_MAX_LENGTH);
    }
}