package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

class AddUserRequestConstantsTest {

    @Test
    void verificarMensajesDeValidacion() {
        assertEquals("El nombre es requerido", AddUserRequestConstants.NAME_REQUIRED);
        assertEquals("El apellido es requerido", AddUserRequestConstants.LASTNAME_REQUIRED);
        assertEquals("El documento de identidad es requerido", AddUserRequestConstants.DOCUMENT_REQUIRED);
        assertEquals("El teléfono es requerido", AddUserRequestConstants.PHONE_REQUIRED);
        assertEquals("La fecha de nacimiento es requerida", AddUserRequestConstants.BIRTHDATE_REQUIRED);
        assertEquals("El correo electrónico es requerido", AddUserRequestConstants.EMAIL_REQUIRED);
        assertEquals("La contraseña es requerida", AddUserRequestConstants.PASSWORD_REQUIRED);
    }

    @Test
    void verificarPatronesRegex() {
        assertTrue("Juan".matches(AddUserRequestConstants.NAME_REGEX));
        assertFalse("Juan123".matches(AddUserRequestConstants.NAME_REGEX));

        assertTrue("1234567890".matches(AddUserRequestConstants.DOCUMENT_REGEX));
        assertFalse("ABC123".matches(AddUserRequestConstants.DOCUMENT_REGEX));

        assertTrue("+573005698325".matches(AddUserRequestConstants.PHONE_REGEX));
        assertFalse("123".matches(AddUserRequestConstants.PHONE_REGEX));

        assertTrue("test@example.com".matches(AddUserRequestConstants.EMAIL_REGEX));
        assertFalse("invalid-email".matches(AddUserRequestConstants.EMAIL_REGEX));

        assertTrue("Password1@".matches(AddUserRequestConstants.PASSWORD_REGEX));
        assertFalse("weak".matches(AddUserRequestConstants.PASSWORD_REGEX));
    }

    @Test
    void verificarNumerosValidacion() {
        assertEquals(13, AddUserRequestConstants.PHONE_MAX_LENGTH);
        assertEquals(8, AddUserRequestConstants.PASSWORD_MIN_LENGTH);
    }

    @Test
    void constructorDeberiaSerPrivado() {
        Constructor<AddUserRequestConstants> constructor = null;
        try {
            constructor = AddUserRequestConstants.class.getDeclaredConstructor();
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