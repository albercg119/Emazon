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
    void verificarCodigosRespuesta() {
        // 201
        assertEquals("201", UserControllerConstants.RESPONSE_201_CODE);
        assertEquals("Usuario creado exitosamente", UserControllerConstants.RESPONSE_201_MESSAGE);

        // 400
        assertEquals("400", UserControllerConstants.RESPONSE_400_CODE);
        assertEquals("Datos de entrada inválidos", UserControllerConstants.RESPONSE_400_MESSAGE);

        // 500
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
