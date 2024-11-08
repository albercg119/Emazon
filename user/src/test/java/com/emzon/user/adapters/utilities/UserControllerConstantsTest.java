package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerConstantsTest {

    @Test
    void verificarRutas() {
        assertEquals("/users", UserControllerConstants.BASE_PATH);
        assertEquals("/create", UserControllerConstants.CREATE_PATH);
        assertEquals("/count", UserControllerConstants.COUNT_PATH);
    }

    @Test
    void verificarDocumentacionSwagger() {
        assertEquals("Users", UserControllerConstants.TAG_NAME);
        assertEquals("API para la gestión de usuarios", UserControllerConstants.TAG_DESCRIPTION);
        assertEquals("Crear un nuevo usuario auxiliar de bodega", UserControllerConstants.OPERATION_SUMMARY);
        assertEquals("Crea un nuevo usuario con rol de auxiliar de bodega en el sistema",
                UserControllerConstants.OPERATION_DESCRIPTION);
    }

    @Test
    void verificarDocumentacionCount() {
        assertEquals("Get total number of users", UserControllerConstants.COUNT_OPERATION_SUMMARY);
        assertEquals("Returns the total count of registered users in the system",
                UserControllerConstants.COUNT_OPERATION_DESCRIPTION);
    }

    @Test
    void verificarCodigosRespuesta() {
        assertEquals("200", UserControllerConstants.RESPONSE_200_CODE);
        assertEquals("Successfully retrieved count", UserControllerConstants.RESPONSE_200_MESSAGE);
        assertEquals("201", UserControllerConstants.RESPONSE_201_CODE);
        assertEquals("Usuario creado exitosamente", UserControllerConstants.RESPONSE_201_MESSAGE);
        assertEquals("400", UserControllerConstants.RESPONSE_400_CODE);
        assertEquals("Datos de entrada inválidos", UserControllerConstants.RESPONSE_400_MESSAGE);
        assertEquals("500", UserControllerConstants.RESPONSE_500_CODE);
        assertEquals("Error interno del servidor", UserControllerConstants.RESPONSE_500_MESSAGE);
    }

    @Test
    void constructor_DeberiaSerPrivado() {
        Constructor<UserControllerConstants> constructor = null;
        try {
            constructor = UserControllerConstants.class.getDeclaredConstructor();
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