package com.emzon.user.domain.utilities.constants;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

class UserUseCaseConstantsTest {

    @Test
    void verificarMensajesDeError() {
        assertEquals("El usuario debe ser mayor de edad",
                UserUseCaseConstants.ADULT_AGE_ERROR);
        assertEquals("El correo electrónico ya se encuentra registrado",
                UserUseCaseConstants.EMAIL_EXISTS_ERROR);
        assertEquals("El número de documento ya se encuentra registrado",
                UserUseCaseConstants.DOCUMENT_EXISTS_ERROR);
    }

    @Test
    void verificarReglasNegocio() {
        assertEquals(18, UserUseCaseConstants.MINIMUM_AGE);
    }

    @Test
    void constructorDeberiaSerPrivado() {
        Constructor<UserUseCaseConstants> constructor = null;
        try {
            constructor = UserUseCaseConstants.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
            assertEquals(IllegalStateException.class, exception.getCause().getClass());
            assertEquals("Utility class", exception.getCause().getMessage());
        } catch (NoSuchMethodException e) {
            fail("Constructor no encontrado");
        }
    }
}