package com.Emazon.Stock.domain.utilities.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryUseCaseConstantsTest {
    @Test
    void constructor_ShouldThrowException() throws NoSuchMethodException {
        Constructor<CategoryUseCaseConstants> constructor = CategoryUseCaseConstants.class.getDeclaredConstructor();
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
        assertEquals("nombre", CategoryUseCaseConstants.FIELD_NAME);
        assertEquals("descripcion", CategoryUseCaseConstants.FIELD_DESCRIPTION);
        assertEquals("id", CategoryUseCaseConstants.FIELD_ID);
    }

    @Test
    void lengthConstants_ShouldHaveCorrectValues() {
        assertEquals(50, CategoryUseCaseConstants.MAX_CATEGORY_NAME_LENGTH);
        assertEquals(90, CategoryUseCaseConstants.MAX_CATEGORY_DESCRIPTION_LENGTH);
        assertEquals(1, CategoryUseCaseConstants.MIN_CATEGORY_NAME_LENGTH);
    }

    @Test
    void defaultConstants_ShouldHaveCorrectValues() {
        assertEquals("Sin descripción", CategoryUseCaseConstants.DEFAULT_CATEGORY_DESCRIPTION);
    }

    @Test
    void patternConstants_ShouldHaveCorrectValues() {
        assertEquals("^[a-zA-Z0-9\\s]{1,50}$", CategoryUseCaseConstants.CATEGORY_NAME_PATTERN);
    }

    @Test
    void validationMessageConstants_ShouldHaveCorrectValues() {
        assertEquals("La categoría no puede ser nula", CategoryUseCaseConstants.CATEGORY_NULL_EXCEPTION_MESSAGE);
        assertEquals("El nombre de la categoría no puede estar vacío", CategoryUseCaseConstants.CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE);
        assertEquals("El nombre solo puede contener letras, números y espacios", CategoryUseCaseConstants.CATEGORY_NAME_INVALID_FORMAT_MESSAGE);
        assertEquals("La descripción de la categoría no puede estar vacía", CategoryUseCaseConstants.CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE);
        assertEquals("El nombre de la categoría debe ser único", CategoryUseCaseConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
    }

    @Test
    void paginationConstants_ShouldHaveCorrectValues() {
        assertEquals(10, CategoryUseCaseConstants.DEFAULT_PAGE_SIZE);
        assertEquals(0, CategoryUseCaseConstants.DEFAULT_PAGE_NUMBER);
        assertEquals("El tamaño de página debe ser mayor que 0", CategoryUseCaseConstants.INVALID_PAGE_SIZE_MESSAGE);
        assertEquals("El número de página debe ser mayor o igual a 0", CategoryUseCaseConstants.INVALID_PAGE_NUMBER_MESSAGE);
        assertEquals("La dirección de ordenamiento no es válida", CategoryUseCaseConstants.INVALID_SORT_DIRECTION_MESSAGE);
    }

    @Test
    void formattedMessageConstants_ShouldBeCorrectlyFormatted() {
        String expectedNameLengthMessage = "La longitud del nombre debe estar entre 1 y 50 caracteres";
        String expectedDescriptionLengthMessage = "La descripción no puede exceder los 90 caracteres";

        assertEquals(expectedNameLengthMessage, CategoryUseCaseConstants.CATEGORY_NAME_LENGTH_MESSAGE);
        assertEquals(expectedDescriptionLengthMessage, CategoryUseCaseConstants.CATEGORY_DESCRIPTION_LENGTH_MESSAGE);
    }
}