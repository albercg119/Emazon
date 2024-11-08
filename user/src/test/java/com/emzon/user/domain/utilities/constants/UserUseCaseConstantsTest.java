package com.emzon.user.domain.utilities.constants;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class UserUseCaseConstantsTest {

    @Test
    void verificarMensajesDeError() {
        assertEquals("El usuario debe ser mayor de edad", UserUseCaseConstants.ADULT_AGE_ERROR);
        assertEquals("El correo electrónico ya se encuentra registrado", UserUseCaseConstants.EMAIL_EXISTS_ERROR);
        assertEquals("El número de documento ya se encuentra registrado", UserUseCaseConstants.DOCUMENT_EXISTS_ERROR);
        assertEquals("Usuario no encontrado con el correo: ", UserUseCaseConstants.USER_NOT_FOUND);
        assertEquals("El correo electrónico ya existe", UserUseCaseConstants.EMAIL_ALREADY_EXISTS);
        assertEquals("El documento de identidad ya existe", UserUseCaseConstants.DOCUMENT_ID_EXISTS);
    }

    @Test
    void verificarConstantesNumericas() {
        assertEquals(18, UserUseCaseConstants.MINIMUM_AGE);
        assertEquals(0, UserUseCaseConstants.MINIMUM_COUNT);
    }

    @Test
    void constructorDeberiaSerPrivado() {
        Constructor<UserUseCaseConstants> constructor = null;
        try {
            constructor = UserUseCaseConstants.class.getDeclaredConstructor();

            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    "El constructor debería ser privado");

            constructor.setAccessible(true);
            InvocationTargetException exception = assertThrows(
                    InvocationTargetException.class,
                    constructor::newInstance,
                    "Debería lanzar InvocationTargetException"
            );

            Throwable cause = exception.getCause();
            assertNotNull(cause, "La causa de la excepción no debería ser null");
            assertEquals(IllegalStateException.class, cause.getClass(),
                    "La causa debería ser IllegalStateException");
            assertEquals("Utility class", cause.getMessage(),
                    "El mensaje de error no coincide");

        } catch (NoSuchMethodException e) {
            fail("No se encontró el constructor por defecto");
        }
    }
}