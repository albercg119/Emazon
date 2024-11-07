package com.Emazon.Stock.configuration.utilities.constants;

import com.Emazon.Stock.domain.utilities.constants.CategoryUseCaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class CategoryUseCaseConstantsTest {

    @Test
    void constantValues_ShouldHaveCorrectValues() {
        // Verificar mensajes de error
        Assertions.assertEquals("La categoría no puede ser nula",
                CategoryUseCaseConstants.CATEGORY_NULL_EXCEPTION_MESSAGE,
                "Mensaje de categoría nula incorrecto");

        assertEquals("El nombre de la categoría no puede ser nulo o vacío",
                CategoryUseCaseConstants.CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE,
                "Mensaje de nombre nulo o vacío incorrecto");

        assertEquals("El nombre de la categoría no puede tener más de 50 caracteres",
                CategoryUseCaseConstants.CATEGORY_NAME_LENGTH_MESSAGE,
                "Mensaje de longitud de nombre incorrecto");

        assertEquals("La descripción de la categoría no puede ser nula o vacía",
                CategoryUseCaseConstants.CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE,
                "Mensaje de descripción nula o vacía incorrecto");

        assertEquals("La descripción de la categoría no puede tener más de 90 caracteres",
                CategoryUseCaseConstants.CATEGORY_DESCRIPTION_LENGTH_MESSAGE,
                "Mensaje de longitud de descripción incorrecto");

        assertEquals("Ya existe una categoría con ese nombre",
                CategoryUseCaseConstants.CATEGORY_ALREADY_EXISTS_MESSAGE,
                "Mensaje de categoría existente incorrecto");
    }

    @Test
    void numericConstants_ShouldHaveCorrectValues() {
        // Verificar constantes numéricas
        assertEquals(50, CategoryUseCaseConstants.MAX_NAME_LENGTH,
                "Longitud máxima de nombre incorrecta");
        assertEquals(90, CategoryUseCaseConstants.MAX_DESCRIPTION_LENGTH,
                "Longitud máxima de descripción incorrecta");
    }

    @Test
    void constructor_ShouldBePrivate() {
        Constructor<CategoryUseCaseConstants> constructor = null;
        try {
            constructor = CategoryUseCaseConstants.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "Constructor debe ser privado");
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }

    @Test
    void constructor_ShouldThrowException() {
        Constructor<CategoryUseCaseConstants> constructor = null;
        try {
            constructor = CategoryUseCaseConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Exception exception = assertThrows(InvocationTargetException.class,
                    constructor::newInstance,
                    "Constructor debe lanzar excepción");

            Throwable cause = exception.getCause();
            assertNotNull(cause, "La causa de la excepción no debe ser null");
            assertEquals(IllegalStateException.class, cause.getClass(),
                    "Debe ser IllegalStateException");
            assertEquals("Utility class", cause.getMessage(),
                    "Mensaje de excepción incorrecto");

        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }

    @Test
    void messageConstants_ShouldBeConsistentWithNumericConstants() {
        // Verificar que los mensajes de error contengan los valores numéricos correctos
        assertTrue(CategoryUseCaseConstants.CATEGORY_NAME_LENGTH_MESSAGE
                        .contains(String.valueOf(CategoryUseCaseConstants.MAX_NAME_LENGTH)),
                "El mensaje de longitud de nombre debe contener el valor máximo");

        assertTrue(CategoryUseCaseConstants.CATEGORY_DESCRIPTION_LENGTH_MESSAGE
                        .contains(String.valueOf(CategoryUseCaseConstants.MAX_DESCRIPTION_LENGTH)),
                "El mensaje de longitud de descripción debe contener el valor máximo");
    }
}