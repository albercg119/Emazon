package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

class UserMappersConstantsTest {

    @Test
    void verificarConstantesDeSistema() {
        assertEquals("spring", UserMappersConstants.SPRING_COMPONENT_MODEL);
        assertEquals("id", UserMappersConstants.TARGET_ID);
        assertEquals("role", UserMappersConstants.TARGET_ROLE);
        assertEquals("AUXILIARY_WAREHOUSE", UserMappersConstants.AUXILIARY_WAREHOUSE_ROLE);
    }

    @Test
    void constructorDeberiaSerPrivado() {
        Constructor<UserMappersConstants> constructor = null;
        try {
            constructor = UserMappersConstants.class.getDeclaredConstructor();
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